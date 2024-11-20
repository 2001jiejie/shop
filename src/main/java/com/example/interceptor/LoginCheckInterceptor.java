package com.example.interceptor;

import com.example.utils.JwtUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import io.jsonwebtoken.Claims;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.example.context.BaseContext;

@Component
public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURL().toString();
        if (url.contains("/login") || url.contains("/register") || url.contains("/favicon.ico")) {
            return true;
        }

        String jwt = (String) request.getSession().getAttribute("jwt");
        if (jwt == null || jwt.isEmpty()) {
            //response.sendRedirect("/login");
            return false;
        }

        try {
            Claims claims = JwtUtils.parseJWT(jwt);
            String userIdStr = claims.getSubject();
            Integer userId = Integer.parseInt(userIdStr);
            BaseContext.setCurrentId(userId); // 设置当前用户 ID
        } catch (Exception e) {
            //response.sendRedirect("/login");
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