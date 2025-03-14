package com.pei.dehaze.filter;

import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import cn.hutool.jwt.RegisteredPayload;
import com.pei.dehaze.common.constant.SecurityConstants;
import com.pei.dehaze.common.result.ResultCode;
import com.pei.dehaze.common.util.ResponseUtils;
import com.pei.dehaze.security.util.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * JWT token 校验过滤器
 *
 * @author Ray Hao
 * @since 2023/9/13
 */
public class JwtValidationFilter extends OncePerRequestFilter {

    private final RedisTemplate<String, Object> redisTemplate;

    private final byte[] secretKey;

    public JwtValidationFilter(RedisTemplate<String, Object> redisTemplate, String secretKey) {
        this.redisTemplate = redisTemplate;
        this.secretKey = secretKey.getBytes();
    }


    /**
     * 从请求中获取 JWT Token，校验 JWT Token 是否合法
     * <p>
     * 如果合法则将 Authentication 设置到 Spring Security Context 上下文中
     * 如果不合法则清空 Spring Security Context 上下文，并直接返回响应
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, @NotNull HttpServletResponse response,
                                    @NotNull FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);

        try {
            if (CharSequenceUtil.isNotBlank(token) && token.startsWith(SecurityConstants.JWT_TOKEN_PREFIX)) {
                token = token.substring(SecurityConstants.JWT_TOKEN_PREFIX.length()); // 去除 Bearer 前缀
                // 校验 Token 是否有效
                if (JWTUtil.verify(token, secretKey)) {
                    // 解析 Token 获取有效载荷
                    JWT jwt = JWTUtil.parseToken(token);
                    JSONObject payloads = jwt.getPayloads();

                    // 检查 Token 是否已被加入黑名单
                    String jti = payloads.getStr(RegisteredPayload.JWT_ID);
                    boolean isTokenBlacklisted = Boolean.TRUE.equals(redisTemplate.hasKey(SecurityConstants.BLACKLIST_TOKEN_PREFIX + jti));
                    if (isTokenBlacklisted) {
                        ResponseUtils.writeErrMsg(response, ResultCode.TOKEN_INVALID);
                        return;
                    }
                    // Token 有效将其解析为 Authentication 对象，并设置到 Spring Security 上下文中
                    Authentication authentication = JwtUtils.getAuthentication(payloads);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                } else {
                    // Token 无效，直接返回响应
                    ResponseUtils.writeErrMsg(response, ResultCode.TOKEN_INVALID);
                    return;
                }
            }
        } catch (Exception e) {
            SecurityContextHolder.clearContext();
            ResponseUtils.writeErrMsg(response, ResultCode.TOKEN_INVALID);
            return;
        }
        // Token有效或无Token时继续执行过滤链
        filterChain.doFilter(request, response);
    }
}
