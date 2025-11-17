package com.wlog.wlogweb.service.user;

import com.wlog.wlogcommon.domain.dos.AdminUserDO;
import com.wlog.wlogweb.controller.user.vo.UserSaveReqVO;

import javax.validation.Valid;

/**
 * 后台用户 Service 接口
 *
 * @author wsw
 */
public interface AdminUserService {
    /**
     * 创建用户
     *
     * @param createReqVO 用户信息
     * @return 用户编号
     */
    Long createUser(@Valid UserSaveReqVO createReqVO);


    /**
     * 通过用户名查询用户
     *
     * @param username 用户名
     * @return 用户对象信息
     */
    AdminUserDO getUserByUsername(String username);


    /**
     * 判断密码是否匹配
     *
     * @param rawPassword 未加密的密码
     * @param encodedPassword 加密后的密码
     * @return 是否匹配
     */
    boolean isPasswordMatch(String rawPassword, String encodedPassword);
}
