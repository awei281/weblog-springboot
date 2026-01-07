package com.wlog.wlogcommon.mq.rabbitmq;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.wlog.wlogcommon.domain.dos.MqDeadMessageDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;

@Component
@Slf4j
public class MQMessageSender implements MQMessageSenderService{

    @Resource
    private RabbitTemplate rabbitTemplate;

    public void send(Object str, String exchangeName, String routingKey) {
        log.info("发送消息：{}", str);
        MessagePostProcessor messagePostProcessor = message -> {
            message.getMessageProperties().setHeader("consumerExchangeName", exchangeName);
            message.getMessageProperties().setHeader("consumerRoutingKey", routingKey);
            return message;
        };
        rabbitTemplate.convertAndSend(
                exchangeName,   // 发到哪个交换机
                routingKey, // 用什么路由键
                str,
                messagePostProcessor// 消息体
        );
    }

    @Override
    public void send(MqDeadMessageDO mqDeadMessageDO) {
        log.info("异常消息重新发布：{}", mqDeadMessageDO);






        MessagePostProcessor messagePostProcessor = message -> {
            message.getMessageProperties().setHeader("replay", true);
            message.getMessageProperties().setHeader("replayFromDeadId", mqDeadMessageDO.getId());
            message.getMessageProperties().setHeader("operator", "王舜威");
            return message;
        };

        String headers = mqDeadMessageDO.getHeaders();
        if (StrUtil.isNotBlank(headers)) {
            HashMap<String, String> originHeaders = JSONUtil.toBean(headers, HashMap.class);
            String messageBody = mqDeadMessageDO.getMessageBody();
            log.info("原消息具体内容是：{}", messageBody);
            messageBody="哈哈哈";
            rabbitTemplate.convertAndSend(
                    originHeaders.get("consumerExchangeName"),
                    originHeaders.get("consumerRoutingKey"),
                    messageBody,
                    messagePostProcessor
            );
        }
        /**
         * // 新的请求 ID
         * props.setHeader("requestId", UUID.randomUUID().toString());
         * // 关联原消息
         * props.setHeader("replayFromDeadId", deadMessage.getId());
         * // 标识这是一次重放
         * props.setHeader("replay", true);
         * // 操作人（人工触发）
         * props.setHeader("operator", currentUser);
         * // 原始业务标识（可选）
         * props.setHeader("bizKey", deadMessage.getBizKey());
         */

    }

    @Override
    public void send(Object message, MessagePostProcessor messagePostProcessor) {


    }

}
