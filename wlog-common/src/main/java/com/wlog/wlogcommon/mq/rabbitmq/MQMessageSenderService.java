package com.wlog.wlogcommon.mq.rabbitmq;

import com.wlog.wlogcommon.domain.dos.MqDeadMessageDO;
import org.springframework.amqp.core.MessagePostProcessor;

/**
 * @author： wsw
 * @date： 2025/12/27 14:46
 * @describe：
 */
public interface MQMessageSenderService {

     void send(Object message,String exchangeName, String routingKey);

     void send(MqDeadMessageDO mqDeadMessageDO);

     void send(Object message, MessagePostProcessor messagePostProcessor);
}
