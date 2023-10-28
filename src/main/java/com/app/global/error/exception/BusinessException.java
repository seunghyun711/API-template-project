package com.app.global.error.exception;

import com.app.global.error.ErrorCode;
import lombok.Getter;

/**
 * 비즈니스 로직 수행 중 예외를 발생시켜야 하는 경우 사용할 Custom Exception 정의
 */
@Getter
public class BusinessException extends RuntimeException {
    private ErrorCode errorCode;

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
