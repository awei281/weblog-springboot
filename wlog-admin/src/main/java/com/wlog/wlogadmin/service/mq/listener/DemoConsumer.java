package com.wlog.wlogadmin.service.mq.listener;

import com.rabbitmq.client.Channel;
import com.wlog.wlogcommon.mq.rabbitmq.AbstractRabbitConsumer;
import com.wlog.wlogcommon.mq.rabbitmq.MqConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

@Slf4j
@Component
@RabbitListener(queues = MqConstants.TEST_QUEUE)
public class DemoConsumer extends AbstractRabbitConsumer<String> {

    /**
     * @param message 反序列化后的业务数据
     * @param channel RabbitMQ 原生通道
     * @param msg     原始消息（header、deliveryTag 在这）
     * @throws IOException 抛给 RabbitMQ
     */
    @RabbitHandler
    public void onMessage(String message, Channel channel, Message msg) throws IOException {
        consume(message, channel, msg);
    }


    @Override
    protected void handleMessage(String data, Message message) {
        log.info("处理业务消息：{}", data);
        // 模拟异常
        if ("error1".equals(data)) {
            throw new RuntimeException("模拟业务异常");
        }
    }

    @Override
    protected String getBizKey(String data) {
        return UUID.randomUUID().toString();
    }

    @Override
    protected String consumerCode() {
        return Thread.currentThread().getId() + MqConstants.TEST_QUEUE;
    }
}
