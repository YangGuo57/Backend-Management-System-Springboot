package com.yguo57.interceptors;

import com.yguo57.pojo.Result;
import com.yguo57.utils.JwtUtil;
import com.yguo57.utils.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // verify token
        String token = request.getHeader("Authorization");
        // verify user token (stored in the brower header)
        try {
            Map<String, Object> claims = JwtUtil.parseToken(token);
            // store data into ThreadLocal
            ThreadLocalUtil.set(claims);
            // allow through
            return true;
        } catch (Exception e) {
            response.setStatus(401);
            // deny through
            return false;
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // clear data from ThreadLocal to prevent memory leak
        ThreadLocalUtil.remove();
    }
}
