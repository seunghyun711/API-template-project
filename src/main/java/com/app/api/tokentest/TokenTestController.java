package com.app.api.tokentest;

import com.app.domain.member.constant.Role;
import com.app.global.jwt.dto.JwtTokenDto;
import com.app.global.jwt.service.TokenManager;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/token-test")
@RequiredArgsConstructor
public class TokenTestController {
    private final TokenManager tokenManager;

    @GetMapping("/create") // 토큰 생성 및 만료시간 생성 테스트
    public JwtTokenDto createJwtTokenDto() {
        return tokenManager.createJwtTokenDto(1L, Role.ADMIN);
    }

    @GetMapping("/valid") // 토큰이 들어왔을 때 해당 토큰이 유효한지 검증하는 요청
    public String validateJwtToken(@RequestParam String token) {
        tokenManager.validateToken(token);
        Claims tokenClaims = tokenManager.getTokenClaims(token); // 토큰 정보 조회
        Long memberId = Long.valueOf((Integer) tokenClaims.get("memberId"));
        String role = (String) tokenClaims.get("role");
        log.info("memberId : {}", memberId);
        log.info("role : {}", role);

        return "success";
    }
}
