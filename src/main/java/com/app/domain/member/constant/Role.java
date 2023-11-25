package com.app.domain.member.constant;

// 회원 역할
public enum Role {
    USER, ADMIN;

    public static Role from(String role) {
        return Role.valueOf(role);
    }

}
