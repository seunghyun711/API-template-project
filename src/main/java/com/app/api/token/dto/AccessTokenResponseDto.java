package com.app.api.token.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter @Builder
public class AccessTokenResponseDto { // 액세스 토큰을 응답하기 위한 dto

    @Schema(description = "grantType", example = "Bearer", required = true)
    private String grantType;

    @Schema(description = "accessToken", example = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJBQ0NFU1MiLCJpYXQiOjE3MDQxMTcyMzAsImV4cCI6MTcwNDExODEzMCwibWVtYmVySWQiOjEsInJvbGUiOiJBRE1JTiJ9.qxDNJz9-mURp4Y_TEez42G2Om2GdBwDEwLWL__3NsH6EbbYjXfTpXThqpJyO5g8gpim_5bXiEstGq0QfGWWBPw", required = true)
    private String accessToken;

    @Schema(description = "access token 만료 시간", example = "2024-01-01 23:08:50", required = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private Date accessTokenExpireTime;
}
