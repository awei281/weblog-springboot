package com.wlog.wlogcommon.redis;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @author： wsw
 * @date： 2025/12/28 14:42
 * @describe：
 */
@Repository
public class BuildRedisCacheDAO {

    @Resource
    private StringRedisTemplate stringRedisTemplate;




}
