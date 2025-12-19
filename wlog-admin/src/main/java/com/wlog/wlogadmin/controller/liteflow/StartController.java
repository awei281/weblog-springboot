package com.wlog.wlogadmin.controller.liteflow;

import com.wlog.wlogadmin.service.liteflow.LiteFlowService;
import com.wlog.wlogcommon.utils.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;

/**
 * @author： wsw
 * @date： 2025/12/18 15:08
 * @describe： 开始控制层
 */
@RestController
@RequestMapping("/lite_flow")
@Tag(name = "lite_flow模块")
public class StartController {

    @Resource
    private LiteFlowService liteFlowService;

    @PostMapping("/start")
    @Operation(summary = "开始")
    @PermitAll
    public Response<String> publishArticle() {
        return Response.success( liteFlowService.start()) ;
    }

}
