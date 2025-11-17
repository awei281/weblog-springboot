package com.wlog.wlogcommon.domain.dos;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * OAuth2 访问令牌 DO
 * 如下字段，暂时未使用，暂时不支持：
 * user_name、authentication（用户信息）
 *
 * @author wsw
 */
@TableName(value = "oauth2_access_token", autoResultMap = true)
@Data
@Accessors(chain = true)

public class OAuth2AccessTokenDO {

    /**
     * 编号，数据库递增
     */
    @TableId
    private Long id;
    /**
     * 访问令牌
     */
    private String accessToken;
    /**
     * 刷新令牌
     */
    private String refreshToken;
    /**
     * 用户编号
     */
    private Long userId;

    /**
     * 过期时间
     */
    private LocalDateTime expiresTime;

}
