package com.app.global.jwt.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JwtTokenDto {
    private String grantType; // grantType
    private String accessToken; // 액세스 토큰
    private Date accessTokenExpireTime; // 액세스 토큰 만료 시간
    private String refreshToken; // 리프레시 토큰
    private Date refreshTokenExpireTime; // 리프레시 토큰 만료 시간
}
