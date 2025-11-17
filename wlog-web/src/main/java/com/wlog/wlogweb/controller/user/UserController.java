package com.wlog.wlogweb.controller.user;

import com.wlog.wlogcommon.utils.Response;
import com.wlog.wlogweb.controller.user.vo.UserSaveReqVO;
import com.wlog.wlogweb.service.user.AdminUserService;
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
 * @date： 2025/11/17 15:45
 * @describe：
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Resource
    private AdminUserService userService;

    @PostMapping("/create")
    @Operation(summary = "创建用户")
    public Response<Long> createUser(@RequestBody @Valid UserSaveReqVO reqVO) {
        return Response.success(userService.createUser(reqVO));
    }
}
