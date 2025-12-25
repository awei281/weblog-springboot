package com.wlog.wlogadmin.service.mq;

import com.wlog.wlogadmin.controller.mq.vo.MqTestVO;
import com.wlog.wlogcommon.mq.rabbitmq.MQMessageSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author： wsw
 * @date： 2025/12/25 21:20
 * @describe：
 */
@Service
@Slf4j
public class RabbitMQServiceImpl implements RabbitMQService {

    @Resource
    private MQMessageSender messageSender;

    @Override
    public void sendMsg(MqTestVO msg) {
        try {
            messageSender.send(msg.getMessage());
        } catch (Exception e) {
            log.error("发送MQ消息失败", e);
            throw e;
        }
    }
}
