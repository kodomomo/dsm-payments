package com.github.kodomo.dsmpayments.infra.security;

import com.github.kodomo.dsmpayments.infra.exception.GlobalException;
import com.github.kodomo.dsmpayments.infra.token.JWTRequired;
import com.github.kodomo.dsmpayments.infra.token.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@RequiredArgsConstructor
@Component
public class SecurityFilter implements HandlerInterceptor {

    private final TokenProvider tokenProvider;

    @Value("${admin.password}")
    private String adminPassword;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        if (response.getStatus() == 404) {
            throw new GlobalException(404, "Not Found");
        }

        String uri = request.getRequestURI();
        if (uri.startsWith("/admin") && !uri.equals("/admin/auth")) {
            String password = request.getHeader("Authorization");
            if (!adminPassword.equals(password)) {
                response.sendError(403, "Forbidden");
                return false;
            }
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Class<?> handlerClass = handlerMethod.getBeanType();

        boolean jwtRequired = false;
        if (handlerMethod.hasMethodAnnotation(JWTRequired.class) || handlerClass.getDeclaredAnnotation(JWTRequired.class) != null) {
            jwtRequired = true;
        }
        if (jwtRequired) {
            String token = tokenProvider.resolveAccessToken(request);
            if (tokenProvider.validateToken(token)) {
                String uuid = tokenProvider.parseAccessToken(token);
                SecurityContext securityContext = SecurityContextHolder.getContext();
                securityContext.setAuthentication(new Authentication() {
                    @Override
                    public Collection<? extends GrantedAuthority> getAuthorities() {
                        return null;
                    }

                    @Override
                    public Object getCredentials() {
                        return null;
                    }

                    @Override
                    public Object getDetails() {
                        return null;
                    }

                    @Override
                    public Object getPrincipal() {
                        return uuid;
                    }

                    @Override
                    public boolean isAuthenticated() {
                        return false;
                    }

                    @Override
                    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

                    }

                    @Override
                    public String getName() {
                        return null;
                    }
                });
                return true;
            }
            throw new GlobalException(401, "UnAuthenticated");
        }
        return true;
    }

}
