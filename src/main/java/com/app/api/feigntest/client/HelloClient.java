package com.app.api.feigntest.client;

import com.app.api.health.dto.HealthCheckResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
//  HealthCheck API를 호출하는 Feign 클라이언트
@FeignClient(url = "http://localhost:8080", name = "helloClient")
public interface HelloClient {
    @GetMapping(value = "/api/health", consumes = "application/json")
    HealthCheckResponseDto healthCheck();
}
