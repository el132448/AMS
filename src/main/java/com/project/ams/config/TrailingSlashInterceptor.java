package com.project.ams.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class TrailingSlashInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        if (requestURI.endsWith("/") && !requestURI.equals("/")) {
            String newURI = requestURI.substring(0, requestURI.length() - 1);
            response.sendRedirect(newURI);
            return false;
        }
        return true;
    }
}