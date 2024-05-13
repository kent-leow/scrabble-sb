package com.example.scrabblesb.auth.interceptors;

import com.example.scrabblesb.auth.annotations.AuthGuard;
import com.example.scrabblesb.auth.dtos.TokenPayloadDto;
import com.example.scrabblesb.auth.utils.JwtService;
import com.example.scrabblesb.users.enums.Role;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;


@Component
public class AuthInterceptor implements HandlerInterceptor {

    private JwtService jwtService;

    @Autowired
    public AuthInterceptor(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            AuthGuard authGuard = handlerMethod.getMethodAnnotation(AuthGuard.class);
            if (authGuard != null) {
                Role[] roles = authGuard.value();
                String header = request.getHeader("Authorization");
                if (header == null || !header.startsWith("Bearer ")) {
                    response.sendError(HttpStatus.UNAUTHORIZED.value(), "Unauthorized");
                    return false;
                }

                String token = header.substring(7); // Extract token from header (excluding "Bearer ")

                try {
                    TokenPayloadDto tokenPayloadDto = jwtService.extractClaim(token, claims ->
                            TokenPayloadDto.builder()
                                    .role(Role.valueOf((String) claims.get("role")))
                                    .sub((String) claims.get("sub"))
                                    .username((String) claims.get("username"))
                                    .build());
                    if (roles.length > 0 && !Arrays.asList(roles).contains(tokenPayloadDto.getRole())) {
                        response.sendError(HttpStatus.FORBIDDEN.value(), "Forbidden");
                        return false;
                    }
                    request.setAttribute("user", tokenPayloadDto);
                    return true;
                } catch (Exception e) {
                    response.sendError(HttpStatus.UNAUTHORIZED.value(), "Unauthorized");
                    return false;
                }

            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
