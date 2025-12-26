package com.wlog.wlogcommon.mq.rabbitmq;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
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
    /**
     * 最大重试次数
     */
    private static final int MAX_RETRY = 3;

    /**
     * RabbitMQ 消费模板
     * 负责：
     * 1. 调用业务
     * 2. ACK / NACK
     * 3. 异常兜底
     */
    public void consume(T data, Channel channel, Message message) throws IOException {
        // RabbitMQ 用它标识“这是哪一条消息”
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            log.info("开始消费消息：{}", data);
            handleMessage(data);
            channel.basicAck(deliveryTag, false);
        } catch (Exception e) {
            log.error("消费消息出现异常");
            onException(e, data, channel, message, deliveryTag);
        }
        channel.basicAck(deliveryTag, false);
    }

    protected abstract void handleMessage(T data);


    protected void onException(Exception e, T data, Channel channel, Message message, long deliveryTag) throws IOException {
        log.error("处理业务异常", e);
        int retryCount = getRetryCount(message);
        log.info("当前重试次数：{}", retryCount);
        if (retryCount < MAX_RETRY) {
            //投送到延迟队列
            log.error("当前重试次数小于最大重试次数，将消息投递到延迟队列");
            Message retryMsg = buildRetryMessage(message, retryCount + 1);
            rabbitTemplate.send(
                    MqConstants.RETRY_EXCHANGE,
                    MqConstants.RETRY_ROUTING_KEY,
                    retryMsg
            );
        } else {
            //投送到死信队列
            log.error("当前重试次数大于最大重试次数，将消息投递到死信队列");
            channel.basicAck(deliveryTag, false);
//            channel.basicNack(deliveryTag, false, false);
        }
    }

    private int getRetryCount(Message message) {
        Object count = message.getMessageProperties()
                .getHeaders()
                .getOrDefault("x-retry-count", 0);
        return (int) count;
    }

    private Message buildRetryMessage(Message message, int retryCount) {
        MessageProperties props = new MessageProperties();
        props.getHeaders().putAll(message.getMessageProperties().getHeaders());
        props.setHeader("x-retry-count", retryCount);
        return new Message(message.getBody(), props);
    }
}
