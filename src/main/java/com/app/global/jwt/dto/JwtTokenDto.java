package com.app.global.jwt.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul") // 한국 시간에 맞게 만료시간 형식 설정
    private Date accessTokenExpireTime; // 액세스 토큰 만료 시간
    private String refreshToken; // 리프레시 토큰
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Date refreshTokenExpireTime; // 리프레시 토큰 만료 시간
}
