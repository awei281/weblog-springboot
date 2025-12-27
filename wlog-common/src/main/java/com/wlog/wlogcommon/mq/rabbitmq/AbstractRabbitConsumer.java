package com.wlog.wlogcommon.mq.rabbitmq;

import com.rabbitmq.client.Channel;
import com.wlog.wlogcommon.mq.rabbitmq.utils.XDeathUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author： wsw
 * @date： 2025/12/25 22:05
 * @describe： 抽象消费者
 */
@Slf4j
public abstract class AbstractRabbitConsumer<T> {

    @Resource
    private RabbitTemplate rabbitTemplate;

    private static final int MAX_RETRY = 3;

    public void consume(T data, Channel channel, Message message) throws IOException {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            log.info("开始消费消息：{}", data);
            handleMessage(data,message);
            // 1. 成功消费，ACK
            channel.basicAck(deliveryTag, false);
        } catch (Exception e) {
            log.error("消费消息出现异常", e);
            // 2. 异常处理，交由 onException 决定如何 ACK/NACK
            onException(data, channel, message, deliveryTag);
        }
    }

    protected abstract void handleMessage(T data, Message message);

    protected void onException(T data, Channel channel, Message message, long deliveryTag) throws IOException {
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
            // 如果你只是想单纯丢弃消息而不进死信，才用 basicAck
//            channel.basicAck(deliveryTag, false);
        }
    }
}