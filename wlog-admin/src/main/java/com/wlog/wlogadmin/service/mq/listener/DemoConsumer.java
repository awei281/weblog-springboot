package com.wlog.wlogadmin.service.mq.listener;

import com.rabbitmq.client.Channel;
import com.wlog.wlogcommon.mq.rabbitmq.AbstractRabbitConsumer;
import com.wlog.wlogcommon.mq.rabbitmq.MqConstants;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RabbitListener(queues = MqConstants.TEST_QUEUE)
public class DemoConsumer extends AbstractRabbitConsumer<String> {

    /**
     * @param message 反序列化后的业务数据
     * @param channel RabbitMQ 原生通道
     * @param msg 原始消息（header、deliveryTag 在这）
     * @throws IOException 抛给 RabbitMQ
     */
    @RabbitHandler
    public void onMessage(String message, Channel channel, Message msg) throws IOException {
        consume(message, channel, msg);
//        原本消费者需要干的事情
//        try {
//            System.out.println("收到消息：" + message);
//            //手动ack 消费成功可以删除
//            channel.basicAck(msg.getMessageProperties().getDeliveryTag(), false);
//        } catch (Exception e) {
//            //手动nack 消费失败重新入队
//            channel.basicNack(
//                    msg.getMessageProperties().getDeliveryTag(),
//                    false,
//                    true
//            );
//        }
    }


    @Override
    protected void handleMessage(String data) {
        System.out.println("处理业务消息：" + data);

        // 模拟异常
        if ("error1".equals(data)) {
            throw new RuntimeException("模拟业务异常");
        }
    }
}
