package com.wlog.wlogweb.service.auth;

import com.wlog.wlogweb.controller.auth.vo.AuthLoginReqVO;
import com.wlog.wlogweb.controller.auth.vo.AuthLoginRespVO;

import javax.validation.Valid;

/**
 * 管理后台的认证 Service 接口
 * 提供用户的登录、登出的能力
 *
 * @author wsw
 */
public interface AdminAuthService {


    /**
     * 账号登录
     *
     * @param reqVO 登录信息
     * @return 登录结果
     */
    AuthLoginRespVO login(@Valid AuthLoginReqVO reqVO);



}
