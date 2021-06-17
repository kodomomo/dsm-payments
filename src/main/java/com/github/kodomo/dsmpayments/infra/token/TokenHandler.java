package com.github.kodomo.dsmpayments.infra.token;

import com.github.kodomo.dsmpayments.infra.exception.GlobalException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;

@RequiredArgsConstructor
@Component
public class TokenHandler implements HandlerInterceptor {

    private final TokenProvider tokenProvider;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        if (response.getStatus() == 404) {
            throw new GlobalException(404, "Not Found");
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
                int userNumber = tokenProvider.parseAccessToken(token);
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
                        return userNumber;
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
