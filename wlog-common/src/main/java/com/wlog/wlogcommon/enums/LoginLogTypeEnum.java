package com.wlog.wlogcommon.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 登录日志的类型枚举
 */
@Getter
@AllArgsConstructor
public enum LoginLogTypeEnum {

    // 使用账号登录
    LOGIN_USERNAME(100),

    // 自己主动登出
    LOGOUT_SELF(200),
    // 强制退出
    LOGOUT_DELETE(202),
    ;

    /**
     * 日志类型
     */
    private final Integer type;

}
