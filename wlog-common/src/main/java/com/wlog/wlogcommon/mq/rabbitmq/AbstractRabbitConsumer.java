package com.wlog.wlogcommon.mq.rabbitmq;

import cn.hutool.json.JSONUtil;
import com.rabbitmq.client.Channel;
import com.wlog.wlogcommon.domain.dos.MqMessageDO;
import com.wlog.wlogcommon.domain.mapper.MqMessageMapper;
import com.wlog.wlogcommon.mq.rabbitmq.utils.XDeathUtil;
import com.wlog.wlogcommon.redis.CodeBuilderKey;
import com.wlog.wlogcommon.redis.PrefixEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

/**
 * @author： wsw
 * @date： 2025/12/25 22:05
 * @describe： 抽象消费者
 */
@Slf4j
public abstract class AbstractRabbitConsumer<T> {

    @Resource
    private RabbitTemplate rabbitTemplate;
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private MqMessageMapper mqMessageMapper;

    private static final int MAX_RETRY = 3;

    public void consume(T data, Channel channel, Message message) throws IOException {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();

            log.info("开始消费消息：{}", data);

            String bizKey = getBizKey(data);
            String consumer = consumerCode();
            String idemKey = PrefixEnum.MQ.getKey()+ CodeBuilderKey.CONSUMER_INFO.getKey() + bizKey + ":" + consumer;
            //幂等校验
            Boolean locked = stringRedisTemplate.opsForValue()
                    .setIfAbsent(idemKey, "processing", 300, TimeUnit.SECONDS);

            if (Boolean.FALSE.equals(locked)) {
                String state = stringRedisTemplate.opsForValue().get(idemKey);
                log.warn("重复或并发消费，key={}, state={}", idemKey, state);
                channel.basicAck(deliveryTag, false);
                return;
            }

        try {
            handleMessage(data,message);
            storageDB(bizKey,message);
            stringRedisTemplate.opsForValue().set(idemKey, "success", 24, TimeUnit.HOURS);
            // 1. 成功消费，ACK
            channel.basicAck(deliveryTag, false);
        } catch (Exception e) {
            log.error("消费消息出现异常", e);
            // 2. 异常处理，交由 onException 决定如何 ACK/NACK
            onException(channel, message, deliveryTag);
        }
    }

    private void storageDB(String bizKey,Message message) {
        MqMessageDO build = MqMessageDO.builder()
                .bizKey(bizKey)
                .messageType("测试类型")
                .exchangeName(message.getMessageProperties().getReceivedExchange())
                .routingKey(message.getMessageProperties().getReceivedRoutingKey())
                .queueName(message.getMessageProperties().getConsumerQueue())
                .messageBody(new String(message.getBody(), StandardCharsets.UTF_8))
                .headers(JSONUtil.toJsonStr(message.getMessageProperties().getHeaders()))
                .sendStatus(1).build();
        mqMessageMapper.insert(build);

    }

    protected abstract void handleMessage(T data, Message message);

    protected void onException(Channel channel, Message message, long deliveryTag) throws IOException {
        int retryCount = XDeathUtil.getTotalRetryCount(message);
        log.info("当前重试次数：{}", retryCount);
        RetryPolicyEnum policy = RetryPolicyEnum.getByRetryCount(retryCount);

        if (policy != null) {
            log.warn("消费失败，执行第 {} 次重试（{}），投递到延迟队列", retryCount + 1,policy.getDescription());
            // 构建并发送重试消息
            rabbitTemplate.send(
                    MqConstants.RETRY_EXCHANGE,
                    policy.getRoutingKey(),
                    message
            );
            // 【重要修正】发送重试消息成功后，必须 ACK 当前这条失败的消息，将其移除
            channel.basicAck(deliveryTag, false);
        } else {
            log.error("超过最大重试次数 ({})，将消息投递到死信队列（或丢弃）", MAX_RETRY);
            // 【重要修正】要让消息进死信队列，必须 Nack 且 requeue=false
            // 前提是你的队列配置了 x-dead-letter-exchange
            channel.basicNack(deliveryTag, false, false);
        }
    }

    protected abstract String getBizKey(T data);
    protected abstract String consumerCode();
}