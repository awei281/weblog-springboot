package com.wlog.wlogadmin.service.mq;

import com.wlog.wlogadmin.controller.mq.vo.MqDeadMessageReqVO;

import java.util.List;

/**
 * @author： wsw
 * @date： 2025/12/26 18:52
 * @describe：
 */
public interface MqDeadMessageService {

    List<MqDeadMessageReqVO> mqDeadMessageList(MqDeadMessageReqVO mqDeadMessagePageReqVO);

    void mqDeadMessageRetry(MqDeadMessageReqVO mqDeadMessagePageReqVO);

}
