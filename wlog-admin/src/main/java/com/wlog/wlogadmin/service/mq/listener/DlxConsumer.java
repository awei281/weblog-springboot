package com.wlog.wlogadmin.service.mq.listener;

import com.rabbitmq.client.Channel;
import com.wlog.wlogcommon.mq.rabbitmq.AbstractRabbitConsumer;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
//@RabbitListener(queues = MqConstants.DLX_QUEUE)
public class DlxConsumer extends AbstractRabbitConsumer<String> {


    @RabbitHandler
    public void onMessage(String message, Channel channel, Message msg) throws IOException {
        consume(message, channel, msg);
    }


    @Override
    protected void handleMessage(String data) {
        System.out.println("死信队列接收到消息：" + data);

    }
}
