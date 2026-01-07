package com.wlog.wlogadmin.service.mq;

import com.wlog.wlogadmin.controller.mq.vo.MqTestVO;

/**
 * @author： wsw
 * @date： 2025/12/25 21:20
 * @describe：
 */
public interface RabbitMQService {

    void sendMsg(MqTestVO msg);



}
