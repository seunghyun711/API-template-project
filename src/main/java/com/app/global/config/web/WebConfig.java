package com.app.global.config.web;

import com.app.global.interceptor.AuthenticationInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final AuthenticationInterceptor authenticationInterceptor;
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // api 경로로 오는 요청은 cors 설정
                .allowedOrigins("http://localhost:8082") // localhost:8082를 allowOrigin으로 설정
                .allowedMethods(HttpMethod.GET.name(),
                        HttpMethod.POST.name(),
                        HttpMethod.PUT.name(),
                        HttpMethod.PATCH.name(),
                        HttpMethod.DELETE.name(),
                        HttpMethod.OPTIONS.name());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationInterceptor)
                .order(1)
                .addPathPatterns("/api/**") // /api이하 path는 항상 인증 인터셉터를 거치도록 설정
                .excludePathPatterns("/api/oauth/login", // 해당 엔드 포인트를 제외하고 모든 /api로 시작되는 엔드 포인트는 인증 인터셉터를 거침
                        "/api/access-token/issue",
                        "/api/logout",
                        "/api/health");
    }
}
