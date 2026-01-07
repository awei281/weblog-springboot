package com.wlog.wlogadmin.service.mq;

import com.wlog.wlogadmin.controller.mq.vo.MqDeadMessageReqVO;
import com.wlog.wlogcommon.domain.dos.MqDeadMessageDO;
import com.wlog.wlogcommon.domain.mapper.MqDeadMessageMapper;
import com.wlog.wlogcommon.enums.ResponseCodeEnum;
import com.wlog.wlogcommon.exception.BizException;
import com.wlog.wlogcommon.mq.rabbitmq.MQMessageSenderService;
import com.wlog.wlogcommon.utils.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author： wsw
 * @date： 2025/12/26 18:52
 * @describe：
 */
@Service
public class MqDeadMessageServiceImpl implements MqDeadMessageService{

    @Resource
    private MqDeadMessageMapper mqDeadMessageMapper;
    @Resource
    private MQMessageSenderService mqMessageSenderService;

    @Override
    public List<MqDeadMessageReqVO> mqDeadMessageList(MqDeadMessageReqVO mqDeadMessageReqVO) {
        List<MqDeadMessageDO> mqDeadMessageDOS = mqDeadMessageMapper.selectList(null);
        return BeanUtils.toBean(mqDeadMessageDOS, MqDeadMessageReqVO.class);

    }

    @Override
    public void mqDeadMessageRetry(MqDeadMessageReqVO mqDeadMessagePageReqVO) {
        MqDeadMessageDO mqDeadMessageDO = mqDeadMessageMapper.selectById(mqDeadMessagePageReqVO.getId());
        if (mqDeadMessageDO == null){
            throw  new BizException(ResponseCodeEnum.MQ_ERR_MASSAGE_NOT_EXIST);
        }
        mqMessageSenderService.send(mqDeadMessageDO);
    }
}