package com.wlog.wlogweb.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @author： wsw
 * @date： 2025/11/15 14:38
 * @describe：
 */
@Configuration
@Profile("dev")
public class Knife4jConfig {

    @Bean
    public OpenAPI weblogOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Weblog 博客前台接口文档")
                        .description("Spring Boot + Vue3 前后端分离博客项目")
                        .version("1.0")
                        .contact(new Contact()
                                .name("阿威大甜菜")
                                .url("https://github.com/awei281")
                                .email("424332385@qq.com")
                        )
                )
                .externalDocs(new ExternalDocumentation()
                        .description("项目主页")
                        .url("https://www.quanxiaoha.com/")
                );
    }
}
