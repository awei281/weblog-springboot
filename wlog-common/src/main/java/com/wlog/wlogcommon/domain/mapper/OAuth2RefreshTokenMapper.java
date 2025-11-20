package com.wlog.wlogcommon.domain.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wlog.wlogcommon.domain.dos.OAuth2RefreshTokenDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author： wsw
 * @date： 2025/11/17 15:36
 * @describe：
 */
@Mapper
public interface OAuth2RefreshTokenMapper extends BaseMapper<OAuth2RefreshTokenDO> {

   default void deleteByRefresh(String refreshToken){
       delete(new QueryWrapper<OAuth2RefreshTokenDO>().eq("refresh_token", refreshToken));
   };
}
