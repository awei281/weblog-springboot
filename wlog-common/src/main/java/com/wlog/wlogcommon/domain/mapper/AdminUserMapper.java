package com.wlog.wlogcommon.domain.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wlog.wlogcommon.domain.dos.AdminUserDO;

/**
 * @author： wsw
 * @date： 2025/11/17 15:19
 * @describe：
 */
public interface AdminUserMapper extends BaseMapper<AdminUserDO> {


    default AdminUserDO selectByUsername(String username) {
        LambdaQueryWrapper<AdminUserDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AdminUserDO::getUsername, username);
        return selectOne(queryWrapper);
    }
}
