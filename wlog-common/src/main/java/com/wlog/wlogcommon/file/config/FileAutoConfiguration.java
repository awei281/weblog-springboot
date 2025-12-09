package com.wlog.wlogcommon.file.config;

import com.wlog.wlogcommon.file.FileClientFactory;
import com.wlog.wlogcommon.file.FileClientFactoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author： wsw
 * @date： 2025/12/9 17:32
 * @describe： springboot 启动时自动加载 交给ioc容器进行管理
 */
@Configuration(proxyBeanMethods = false)
public class FileAutoConfiguration {

    @Bean
    public FileClientFactory fileClientFactory() {
        return new FileClientFactoryImpl();
    }

}
