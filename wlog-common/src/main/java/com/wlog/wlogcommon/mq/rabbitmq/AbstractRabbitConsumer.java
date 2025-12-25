package com.wlog.wlogcommon.mq.rabbitmq;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;

import java.io.IOException;

/**
 * @author： wsw
 * @date： 2025/12/25 22:05
 * @describe： 抽象消费者
 */
public abstract  class AbstractRabbitConsumer<T> {

    /**
     * RabbitMQ 消费模板
     * 负责：
     * 1. 调用业务
     * 2. ACK / NACK
     * 3. 异常兜底
     */
    public void consume(T data, Channel channel, Message message) throws IOException {

        // RabbitMQ 用它标识“这是哪一条消息”
        long deliveryTag = message
                .getMessageProperties()
                .getDeliveryTag();

        try {
            // 交给子类处理业务
            handleMessage(data);

            // 业务成功，确认消息
            channel.basicAck(deliveryTag, false);

        } catch (Exception e) {

            // 业务异常处理（可扩展）
            onException(e, data);
            // 消费失败，消息重新入队
//            channel.basicNack(deliveryTag, false, true);
            // ❗ false = 不再回原队列
            channel.basicNack(deliveryTag, false, false);

        }
    }

    protected abstract void handleMessage(T data);


    protected void onException(Exception e, T data) {
        System.err.println("消息处理失败：" + data);
        e.printStackTrace();
    }
}
