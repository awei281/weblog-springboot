package com.wlog.wlogcommon.mq.rabbitmq;

import com.wlog.wlogcommon.domain.dos.MqDeadMessageDO;

/**
 * @author： wsw
 * @date： 2025/12/27 14:46
 * @describe：
 */
public interface MQMessageSenderService {

     void send(String message);

     void send(MqDeadMessageDO mqDeadMessageDO);
}
