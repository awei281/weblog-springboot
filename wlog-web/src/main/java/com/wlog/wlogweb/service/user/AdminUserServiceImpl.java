package com.wlog.wlogweb.service.user;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wlog.wlogcommon.domain.dos.AdminUserDO;
import com.wlog.wlogcommon.domain.mapper.AdminUserMapper;
import com.wlog.wlogcommon.enums.ResponseCodeEnum;
import com.wlog.wlogcommon.exception.BizException;
import com.wlog.wlogcommon.utils.BeanUtils;
import com.wlog.wlogweb.controller.user.vo.UserPageReqVO;
import com.wlog.wlogweb.controller.user.vo.UserRespVO;
import com.wlog.wlogweb.controller.user.vo.UserSaveReqVO;
import com.wlog.wlogweb.controller.user.vo.UserUpdReqVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
            throw new BizException(ResponseCodeEnum.USER_NOT_FOUND);
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
    public UserRespVO getUserById(Long id) {
        AdminUserDO user = userMapper.selectById(id);
        if (user == null) {
            throw new BizException(ResponseCodeEnum.USER_NOT_FOUND);
        }
        return BeanUtils.toBean(user, UserRespVO.class);
    }

    @Override
    public boolean isPasswordMatch(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    @Override
    public void updatePassword(UserUpdReqVO reqVO) {
        AdminUserDO user = userMapper.selectById(reqVO.getId());
        if (user == null) {
            throw new BizException(ResponseCodeEnum.USER_NOT_FOUND);
        }
        //检查密码
        user.setPassword(encodePassword(reqVO.getPassword()));
        user.setUpdateTime(LocalDateTime.now());
        userMapper.updateById(user);
    }

    @Override
    public IPage<UserRespVO> getUserPage(UserPageReqVO pageReqVO) {
        // 构建分页对象
        Page<AdminUserDO> page = new Page<>(pageReqVO.getPageNo(), pageReqVO.getPageSize());
        
        // 构建查询条件
        LambdaQueryWrapper<AdminUserDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.hasText(pageReqVO.getUsername()), 
                         AdminUserDO::getUsername, pageReqVO.getUsername())
                   .like(StringUtils.hasText(pageReqVO.getMobile()), 
                         AdminUserDO::getMobile, pageReqVO.getMobile())
                   .eq(pageReqVO.getStatus() != null, 
                       AdminUserDO::getStatus, pageReqVO.getStatus())
                   .orderByDesc(AdminUserDO::getCreateTime);
        
        // 执行分页查询
        IPage<AdminUserDO> userPage = userMapper.selectPage(page, queryWrapper);
        
        // 转换为响应 VO
        IPage<UserRespVO> result = new Page<>(userPage.getCurrent(), userPage.getSize(), userPage.getTotal());
        result.setRecords(BeanUtils.toBean(userPage.getRecords(), UserRespVO.class));
        
        return result;
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }
}
