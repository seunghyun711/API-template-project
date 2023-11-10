package com.app.global.jwt.constant;

import lombok.Getter;

// 어떤 방식으로 인증을 하는지 정보를 나타내는 enum 클래스
@Getter
public enum GrantType {
    BEARER("Bearer");

    GrantType(String type){
        this.type = type;
    }

    private String type;
}
