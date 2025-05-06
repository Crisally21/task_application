package org.web.task_application.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.web.task_application.config.AppSecurityConfig;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class TokenValidationInterceptor implements HandlerInterceptor {
    private final AppSecurityConfig securityConfig;

    public TokenValidationInterceptor(AppSecurityConfig securityConfig) {
        this.securityConfig = securityConfig;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String tokenHeader = request.getHeader("Token");
        if (tokenHeader == null || !tokenHeader.equals(securityConfig.getToken())) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Неверный или отсутствующий заголовок токена");
            return false;
        }
        return true;
    }

}
