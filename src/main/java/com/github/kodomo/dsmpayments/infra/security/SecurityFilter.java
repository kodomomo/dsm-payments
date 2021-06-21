package com.github.kodomo.dsmpayments.infra.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class SecurityFilter implements HandlerInterceptor {

    @Value("${admin.password}")
    private String adminPassword;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        String uri = request.getRequestURI();
        if (uri.startsWith("/admin") && !uri.equals("/admin/auth")) {
            String password = request.getHeader("Authorization");
            if (!adminPassword.equals(password)) {
                response.sendError(403, "Forbidden");
                return false;
            }
        }
        return true;
    }

}
