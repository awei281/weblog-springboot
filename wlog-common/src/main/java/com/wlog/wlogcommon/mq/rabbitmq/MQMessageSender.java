package com.wlog.wlogcommon.mq.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Slf4j
public class MQMessageSender {

    @Resource
    private RabbitTemplate rabbitTemplate;

    public void send(String message) {
        log.info("发送消息：{}", message);
        rabbitTemplate.convertAndSend(
                MqConstants.TEST_EXCHANGE,   // 发到哪个交换机
                MqConstants.TEST_ROUTING_KEY, // 用什么路由键
                message                      // 消息体
        );
    }

}
