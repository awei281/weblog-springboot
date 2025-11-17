package com.wlog.wlogweb.controller.auth;

import com.wlog.wlogcommon.utils.Response;
import com.wlog.wlogweb.controller.auth.vo.AuthLoginReqVO;
import com.wlog.wlogweb.controller.auth.vo.AuthLoginRespVO;
import com.wlog.wlogweb.service.auth.AdminAuthService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author： wsw
 * @date： 2025/11/17 14:51
 * @describe：
 */
@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {

    @Resource
    private AdminAuthService authService;

    @PostMapping("/login")
    @Operation(summary = "使用账号密码登录")
    public Response<AuthLoginRespVO> login(@RequestBody @Valid AuthLoginReqVO reqVO) {
        return Response.success(authService.login(reqVO));
    }
}
