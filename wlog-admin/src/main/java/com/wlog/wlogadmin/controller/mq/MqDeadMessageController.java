package com.wlog.wlogadmin.controller.mq;

import com.wlog.wlogadmin.controller.mq.vo.MqDeadMessageReqVO;
import com.wlog.wlogadmin.service.mq.MqDeadMessageService;
import com.wlog.wlogcommon.aspect.ApiOperationLog;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author： wsw
 * @date： 2025/12/26 18:48
 * @describe：
 */
@RestController
@RequestMapping("/mq/dead")
@Tag(name = "MQ异常信息")
public class MqDeadMessageController {

    @Resource
    private MqDeadMessageService mqDeadMessageService;


    @PostMapping("/list")
    @Operation(summary = "失败信息分页")
    @ApiOperationLog(description = "失败信息分页")
    public List<MqDeadMessageReqVO> mqDeadMessageList(@RequestBody @Validated MqDeadMessageReqVO mqDeadMessagePageReqVO) {
        return mqDeadMessageService.mqDeadMessageList(mqDeadMessagePageReqVO);
    }

    @PostMapping("/retry")
    @Operation(summary = "重新发送")
    @ApiOperationLog(description = "重新发送")
    public void mqDeadMessageRetry(@RequestBody MqDeadMessageReqVO mqDeadMessagePageReqVO) {
        mqDeadMessageService.mqDeadMessageRetry(mqDeadMessagePageReqVO);
    }

}
