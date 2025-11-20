package com.wlog.wlogweb.service.auth;

import com.wlog.wlogcommon.domain.dos.AdminUserDO;
import com.wlog.wlogcommon.domain.dos.OAuth2AccessTokenDO;
import com.wlog.wlogcommon.enums.ResponseCodeEnum;
import com.wlog.wlogcommon.exception.BizException;
import com.wlog.wlogweb.controller.auth.vo.AuthLoginReqVO;
import com.wlog.wlogweb.controller.auth.vo.AuthLoginRespVO;
import com.wlog.wlogweb.service.oauth2.OAuth2TokenService;
import com.wlog.wlogweb.service.user.AdminUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Auth Service 实现类
 *
 * @author htwl
 */
@Service
@Slf4j
public class AdminAuthServiceImpl implements AdminAuthService {

    @Resource
    private AdminUserService userService;
    @Resource
    private OAuth2TokenService oauth2TokenService;


    @Override
    public AuthLoginRespVO login(AuthLoginReqVO reqVO) {
        // 使用账号密码，进行登录
        AdminUserDO user = authenticate(reqVO.getUsername(), reqVO.getPassword());
        // 创建 Token 令牌，记录登录日志
        return createTokenAfterLoginSuccess(user.getId());

    }

    @Override
    public void logout(String token) {
        // 删除访问令牌
        OAuth2AccessTokenDO accessTokenDO = oauth2TokenService.removeAccessToken(token);
        if (accessTokenDO == null) {
            return;
        }
        log.info("用户登出成功, userId: {}", accessTokenDO.getUserId());
    }



    public AdminUserDO authenticate(String username, String password) {
        // 校验账号是否存在
        AdminUserDO user = userService.getUserByUsername(username);
        if (user == null) {
            throw new BizException(ResponseCodeEnum.USER_NOT_FOUND);
        }
        if (!userService.isPasswordMatch(password, user.getPassword())) {
            throw new BizException(ResponseCodeEnum.INCORRECT_ACCOUNT_OR_PASSWORD);
        }
        return user;
    }


    private AuthLoginRespVO createTokenAfterLoginSuccess(Long userId) {
        // 创建访问令牌
        OAuth2AccessTokenDO accessTokenDO = oauth2TokenService.createAccessToken(userId);
        // 构建返回结果
        if (accessTokenDO == null) {
            return null;
        }
        AuthLoginRespVO.AuthLoginRespVOBuilder authLoginRespVO = AuthLoginRespVO.builder();
        authLoginRespVO.userId(accessTokenDO.getUserId());
        authLoginRespVO.accessToken(accessTokenDO.getAccessToken());
        authLoginRespVO.refreshToken(accessTokenDO.getRefreshToken());
        authLoginRespVO.expiresTime(accessTokenDO.getExpiresTime());
        return authLoginRespVO.build();
    }
}
