package com.app.api.health.controller;

import com.app.api.health.dto.HealthCheckResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@Tag(name = "health check", description = "서버 상태 체크 API") // API 그룹 설정
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class HealthCheckController {

    private final Environment environment; // 애플리케이션이 운영환경에서 실행 중인지 개발환경인지 알기 위해 Envrionment 빈 주입

    @Tag(name = "health check")
    @Operation(summary = "서버 Health Check API", description = "현재 서버가 정장적으로 실행이 된 상태인지 검사하는 API") // API 설명
    @GetMapping("/health")
    public ResponseEntity<HealthCheckResponseDto> healthCheck(){
        HealthCheckResponseDto healthCheckResponseDto = HealthCheckResponseDto.builder()
                .health("OK")
                .activeProfiles(Arrays.asList(environment.getActiveProfiles()))
                .build();
        return ResponseEntity.ok(healthCheckResponseDto);
    }
}
