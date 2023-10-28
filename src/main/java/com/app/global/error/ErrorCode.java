package com.app.global.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * 반환할 HttpStatus 값, 에러 코드, 에러 메시지를 관리하는 Enum
 */
@Getter
public enum ErrorCode {
    ;

    ErrorCode(HttpStatus httpStatus, String errorCode, String message) {
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
        this.message = message;
    }

    private HttpStatus httpStatus;
    private String errorCode;
    private String message;
}
