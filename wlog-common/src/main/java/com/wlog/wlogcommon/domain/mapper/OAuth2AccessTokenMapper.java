package com.wlog.wlogcommon.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wlog.wlogcommon.domain.dos.OAuth2AccessTokenDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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
    @Select("SELECT * FROM oauth2_access_token WHERE access_token = #{accessToken}")
    OAuth2AccessTokenDO selectByAccessToken(@Param("accessToken") String accessToken);
}
