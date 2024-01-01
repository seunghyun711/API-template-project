package com.app.api.login.dto;

import com.app.global.jwt.dto.JwtTokenDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.Date;

public class OauthLoginDto { // 로그인 시 요청 및 응답을 위한 dto 클래스
    @Getter @Setter
    public static class Request{
        @Schema(description = "소셜 로그인 회원 타입", example = "KAKAO", required = true)
        private String memberType;
    }

    @Getter @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response{
        @Schema(description = "grantType", example = "Bearer", required = true)
        private String grantType;

        @Schema(description = "accessToken", example = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBQ0NFU1MiLCJpYXQiOjE3MDQxMTcyMzAsImV4cCI6MTcwNDExODEzMCwibWVtYmVySWQiOjEsInJvbGUiOiJBRE1JTiJ9.qxDNJz9-mURp4Y_TEez42G2Om2GdBwDEwLWL__3NsH6EbbYjXfTpXThqpJyO5g8gpim_5bXiEstGq0QfGWWBPw", required = true)
        private String accessToken;

        @Schema(description = "access token 만료 시간", example = "2024-01-01 23:08:50", required = true)
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
        private Date accessTokenExpireTime;

        @Schema(description = "refreshToken", example = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJSRUZSRVNIIiwiaWF0IjoxNzA0MTE3MjMxLCJleHAiOjE3MDUzMjY4MzAsIm1lbWJlcklkIjoxfQ.bjTDqPhU1PNzsWZfchS8u9VkDVANRN5wLCTBFzc8tGK5PsjKnTroWd5BlKZMAwWIwO2glHD9tplqjFGV6ZbTDQ", required = true)
        private String refreshToken;

        @Schema(description = "refresh token 만료 시간", example = "2024-01-15 22:53:50", required = true)
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
        private Date refreshTokenExpireTime;

        public static Response of(JwtTokenDto jwtTokenDto) {
            return Response.builder()
                    .grantType(jwtTokenDto.getGrantType())
                    .accessToken(jwtTokenDto.getAccessToken())
                    .accessTokenExpireTime(jwtTokenDto.getAccessTokenExpireTime())
                    .refreshToken(jwtTokenDto.getRefreshToken())
                    .refreshTokenExpireTime(jwtTokenDto.getRefreshTokenExpireTime())
                    .build();
        }
    }
}
