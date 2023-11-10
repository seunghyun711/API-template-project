package com.app.global.error.exception;

import com.app.global.error.ErrorCode;

// 토큰 예외 처리 클래스
public class AuthenticationException extends BusinessException {
    public AuthenticationException(ErrorCode errorCode) {
        super(errorCode);
    }
}
