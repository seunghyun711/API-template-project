package com.app.global.error.exception;

import com.app.global.error.ErrorCode;

public class EntityNofFoundException extends BusinessException{
    public EntityNofFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
