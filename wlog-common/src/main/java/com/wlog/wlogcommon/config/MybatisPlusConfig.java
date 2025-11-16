package com.wlog.wlogcommon.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author： wsw
 * @date： 2025/11/16 17:49
 * @describe：
 */
@Configuration
@MapperScan("com.wlog.wlogcommon.domain.mapper")
public class MybatisPlusConfig {
}
