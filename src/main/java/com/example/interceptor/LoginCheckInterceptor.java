package com.example.interceptor;

import com.example.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import io.jsonwebtoken.Claims;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.example.context.BaseContext;
@Slf4j
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 记录基本请求信息
        log.info("拦截请求: {}", request.getRequestURI());
        String authorizationHeader = request.getHeader("Authorization");
        log.info("token信息: {}", authorizationHeader);
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            log.error("token无效");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Authorization header is missing or invalid");
            return false;
        }
        String jwt = authorizationHeader.substring(7);
        try {
            Claims claims = JwtUtils.parseJWT(jwt);
            String userIdStr = claims.getSubject();
            Integer userId = Integer.parseInt(userIdStr);
            log.info("当前用户ID: {}", userId);
            BaseContext.setCurrentId(userId);
        } catch (Exception e) {
            log.error("token解析失败: {}", e.getMessage());
            response.sendRedirect("/login");
            return false;
        }
        return true;
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // Post-handle logic
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // After-completion logic
    }
}