package com.wlog.wlogweb.service.oauth2;

import com.wlog.wlogcommon.domain.dos.OAuth2AccessTokenDO;

import java.util.List;

/**
 * OAuth2.0 Token Service 接口
 * 从功能上，和 Spring Security OAuth 的 DefaultTokenServices + JdbcTokenStore 的功能，提供访问令牌、刷新令牌的操作
 *
 * @author htwl
 */
public interface OAuth2TokenService {



    /**
     * 创建访问令牌
     * 注意：该流程中，会包含创建刷新令牌的创建
     * 参考 DefaultTokenServices 的 createAccessToken 方法
     *
     * @param userId 用户编号
     * @return 访问令牌的信息
     */
    OAuth2AccessTokenDO createAccessToken(Long userId);

    /**
     * 校验访问令牌
     *
     * @param accessToken 访问令牌
     * @return 访问令牌的信息
     */
    OAuth2AccessTokenDO checkAccessToken(String accessToken);

    /**
     * 删除访问令牌
     *
     * @param accessToken 访问令牌
     * @return 访问令牌的信息
     */
    OAuth2AccessTokenDO removeAccessToken(String accessToken);
}
