package com.wlog.wlogadmin.controller.mq;

import com.wlog.wlogadmin.controller.mq.vo.MqTestVO;
import com.wlog.wlogadmin.service.mq.RabbitMQService;
import com.wlog.wlogcommon.utils.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;

/**
 * @author： wsw
 * @date： 2025/12/25 21:19
 * @describe：
 */
@RestController
@RequestMapping("/mq")
@Tag(name = "MQ模块")
public class MqTestController {

    @Resource
    private RabbitMQService rabbitMQService;

    @PostMapping("/send")
    @Operation(summary = "发送消息")
    @PermitAll
    public Response<String> send(@RequestBody MqTestVO mqTestVO) {
        rabbitMQService.sendMsg(mqTestVO);
        return Response.success("成功") ;
    }


}
