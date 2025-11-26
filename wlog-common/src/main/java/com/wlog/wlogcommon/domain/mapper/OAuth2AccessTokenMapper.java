package com.wlog.wlogcommon.domain.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wlog.wlogcommon.domain.dos.OAuth2AccessTokenDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author： wsw
 * @date： 2025/11/17 15:38
 * @describe：
 */
@Mapper
public interface OAuth2AccessTokenMapper extends BaseMapper<OAuth2AccessTokenDO> {

    /**
     * 根据访问令牌查询
     *
     * @param accessToken 访问令牌
     * @return OAuth2AccessTokenDO
     */
    default OAuth2AccessTokenDO selectByAccessToken(@Param("accessToken") String accessToken){
        return selectOne(new LambdaQueryWrapper<OAuth2AccessTokenDO>()
                .eq(OAuth2AccessTokenDO::getAccessToken, accessToken));
    };
}
