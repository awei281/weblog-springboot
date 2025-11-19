package com.wlog.wlogcommon.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Security 工具类
 * 用于获取当前登录用户信息
 *
 * @author wsw
 */
public class SecurityFrameworkUtils {

    /**
     * 获得当前认证信息
     *
     * @return 认证信息
     */
    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 获取当前用户 ID
     *
     * @return 用户 ID
     */
    public static Long getLoginUserId() {
        Authentication authentication = getAuthentication();
        if (authentication == null) {
            return null;
        }
        Object principal = authentication.getPrincipal();
        if (principal instanceof Long) {
            return (Long) principal;
        }
        return null;
    }

    /**
     * 判断用户是否已登录
     *
     * @return 是否已登录
     */
    public static boolean isLogin() {
        return getLoginUserId() != null;
    }
}
