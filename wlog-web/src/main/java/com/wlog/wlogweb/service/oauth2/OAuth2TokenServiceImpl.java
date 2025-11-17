package com.wlog.wlogweb.service.oauth2;

import cn.hutool.core.util.IdUtil;
import com.wlog.wlogcommon.domain.dos.OAuth2AccessTokenDO;
import com.wlog.wlogcommon.domain.dos.OAuth2RefreshTokenDO;
import com.wlog.wlogcommon.domain.mapper.OAuth2AccessTokenMapper;
import com.wlog.wlogcommon.domain.mapper.OAuth2RefreshTokenMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * OAuth2.0 Token Service 实现类
 *
 * @author htwl
 */
@Service
public class OAuth2TokenServiceImpl implements OAuth2TokenService {

    @Resource
    private OAuth2RefreshTokenMapper oauth2RefreshTokenMapper;
    @Resource
    private OAuth2AccessTokenMapper oauth2AccessTokenMapper;

    @Override
    @Transactional
    public OAuth2AccessTokenDO createAccessToken(Long userId) {
        // 创建刷新令牌
        OAuth2RefreshTokenDO refreshTokenDO = createOAuth2RefreshToken(userId);
        // 创建访问令牌
        return createOAuth2AccessToken(refreshTokenDO);
    }

    private OAuth2AccessTokenDO createOAuth2AccessToken(OAuth2RefreshTokenDO refreshTokenDO) {

        OAuth2AccessTokenDO accessTokenDO = new OAuth2AccessTokenDO().setAccessToken(generateAccessToken())
                .setUserId(refreshTokenDO.getUserId())
                .setRefreshToken(refreshTokenDO.getRefreshToken())
                .setExpiresTime(LocalDateTime.now().plusSeconds(36000L));
        oauth2AccessTokenMapper.insert(accessTokenDO);
        return accessTokenDO;
    }

    private OAuth2RefreshTokenDO createOAuth2RefreshToken(Long userId) {
        OAuth2RefreshTokenDO refreshToken = new OAuth2RefreshTokenDO().setRefreshToken(generateRefreshToken())
                .setUserId(userId)
                .setExpiresTime(LocalDateTime.now().plusSeconds(360000L));
        oauth2RefreshTokenMapper.insert(refreshToken);
        return refreshToken;
    }


    private static String generateAccessToken() {
        return IdUtil.fastSimpleUUID();
    }

    private static String generateRefreshToken() {
        return IdUtil.fastSimpleUUID();
    }
}
