package com.wlog.wlogcommon.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;

/**
 * @author： wsw
 * @date： 2025/11/16 18:20
 * @describe：
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private OncePerRequestFilter tokenAuthenticationFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable() // 禁用 CSRF 保护,允许 POST 请求
            // 配置 Session 管理，使用无状态模式
            .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            // 配置请求拦截规则
            .authorizeHttpRequests()
                // 登录相关接口放行
                .mvcMatchers("/auth/login").permitAll()
                // Knife4j 文档相关路径放行
                .mvcMatchers("/doc.html", "/webjars/**", "/v3/api-docs/**", "/swagger-resources/**").permitAll()
                // 需要认证的路径
                .mvcMatchers("/wlog/**").authenticated()
                // 其他都需要放行,无需认证
                .anyRequest().permitAll()
            .and()
            // 添加 Token 认证过滤器
            .addFilterBefore(tokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
