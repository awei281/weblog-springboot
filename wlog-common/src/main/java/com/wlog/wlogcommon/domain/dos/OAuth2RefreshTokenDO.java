package com.wlog.wlogcommon.domain.dos;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * OAuth2 刷新令牌
 *
 * @author wsw
 */
@TableName(value = "oauth2_refresh_token", autoResultMap = true)
@Data
@Accessors(chain = true)
public class OAuth2RefreshTokenDO  {

    /**
     * 编号，数据库字典
     */
    private Long id;
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
