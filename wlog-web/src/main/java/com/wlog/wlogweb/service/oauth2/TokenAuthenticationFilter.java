package com.wlog.wlogweb.service.oauth2;

import com.wlog.wlogcommon.domain.dos.OAuth2AccessTokenDO;
import com.wlog.wlogcommon.enums.ResponseCodeEnum;
import com.wlog.wlogcommon.exception.BizException;
import com.wlog.wlogcommon.utils.JsonUtil;
import com.wlog.wlogcommon.utils.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

/**
 * Token 认证过滤器
 * 用于验证请求中的 Token 是否有效
 *
 * @author wsw
 */
@Component
@Slf4j
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    @Resource
    private OAuth2TokenService oauth2TokenService;

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        
        // 1. 从请求头中获取 Token
        String token = getTokenFromRequest(request);
        
        // 2. 验证 Token
        if (StringUtils.hasText(token)) {
            try {
                // 验证 Token 并获取用户信息
                OAuth2AccessTokenDO accessToken = oauth2TokenService.checkAccessToken(token);
                
                if (accessToken != null) {
                    // 将用户信息存入 Security Context
                    UsernamePasswordAuthenticationToken authentication = 
                        new UsernamePasswordAuthenticationToken(
                            accessToken.getUserId(), 
                            null, 
                            Collections.emptyList()
                        );
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    log.debug("用户 [{}] Token 验证成功", accessToken.getUserId());
                }
            } catch (BizException e) {
                // Token 验证失败，返回统一错误格式
                log.warn("Token 验证失败: {}", e.getErrorMessage());
                writeErrorResponse(response, e);
                return;
            } catch (Exception e) {
                log.error("Token 验证异常: {}", e.getMessage(), e);
                writeErrorResponse(response, new BizException(ResponseCodeEnum.SYSTEM_ERROR));
                return;
            }
        }
        
        // 3. 继续执行过滤器链，由 Spring Security 进行权限校验
        // 如果是受保护的路径且没有认证信息，Spring Security 会自动返回 401
        filterChain.doFilter(request, response);
    }

    /**
     * 从请求中提取 Token
     */
    private String getTokenFromRequest(HttpServletRequest request) {
        // 从 Header 中获取
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) {
            return bearerToken.substring(BEARER_PREFIX.length());
        }
        
        // 从请求参数中获取（兼容某些场景）
        String token = request.getParameter("access_token");
        if (StringUtils.hasText(token)) {
            return token;
        }
        
        return null;
    }

    /**
     * 写入错误响应
     */
    private void writeErrorResponse(HttpServletResponse response, BizException e) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        Response<Object> result = Response.fail(e);
        response.getWriter().write(JsonUtil.toJsonString(result));
    }
}
