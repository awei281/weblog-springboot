package com.wlog.wlogweb.service.user;


import com.wlog.wlogcommon.domain.dos.AdminUserDO;
import com.wlog.wlogcommon.domain.mapper.AdminUserMapper;
import com.wlog.wlogcommon.utils.BeanUtils;
import com.wlog.wlogweb.controller.user.vo.UserSaveReqVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;


/**
 * 后台用户 Service 实现类
 *
 * @author wsw
 */
@Service
@Slf4j
public class AdminUserServiceImpl implements AdminUserService {

    @Resource
    private AdminUserMapper userMapper;
    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public Long createUser(UserSaveReqVO createReqVO) {
        AdminUserDO user = BeanUtils.toBean(createReqVO, AdminUserDO.class);
        user.setStatus(1);
        // 加密密码
        user.setPassword(encodePassword(createReqVO.getMobile()));
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        userMapper.insert(user);
        return user.getId();
    }

    @Override
    public void updateUser(UserSaveReqVO updateReqVO) {
        // 查询用户是否存在
        AdminUserDO existUser = userMapper.selectById(updateReqVO.getId());
        if (existUser == null) {
            throw new IllegalArgumentException("用户不存在");
        }
        
        // 更新用户信息
        AdminUserDO updateUser = BeanUtils.toBean(updateReqVO, AdminUserDO.class);
        updateUser.setUpdateTime(LocalDateTime.now());
        userMapper.updateById(updateUser);
    }

    @Override
    public AdminUserDO getUserByUsername(String username) {
        return userMapper.selectByUsername(username);
    }

    @Override
    public boolean isPasswordMatch(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }
}
