package com.app.global.jwt.constant;

// 토큰의 종류를 나타내는 enum 클래스
public enum TokenType {
    ACCESS, REFRESH;

    public static boolean isAccessToken(String tokenType) {
        return TokenType.ACCESS.name().equals(tokenType);
    }
}
