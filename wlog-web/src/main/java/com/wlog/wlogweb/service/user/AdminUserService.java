package com.wlog.wlogweb.service.user;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wlog.wlogcommon.domain.dos.AdminUserDO;
import com.wlog.wlogweb.controller.user.vo.UserPageReqVO;
import com.wlog.wlogweb.controller.user.vo.UserRespVO;
import com.wlog.wlogweb.controller.user.vo.UserSaveReqVO;
import com.wlog.wlogweb.controller.user.vo.UserUpdReqVO;

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
     * 更新用户
     *
     * @param updateReqVO 用户信息
     */
    void updateUser(@Valid UserSaveReqVO updateReqVO);

    /**
     * 根据用户ID查询用户
     *
     * @param id 用户ID
     * @return 用户信息
     */
    UserRespVO getUserById(Long id);


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

    /**
     * 更新用户密码
     *
     * @param reqVO 更新信息
     */
    void updatePassword(@Valid UserUpdReqVO reqVO);

    /**
     * 分页查询用户列表
     *
     * @param pageReqVO 分页查询条件
     * @return 用户分页数据
     */
    IPage<UserRespVO> getUserPage(@Valid UserPageReqVO pageReqVO);
}
