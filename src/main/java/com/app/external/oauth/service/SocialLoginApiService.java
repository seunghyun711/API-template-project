package com.app.external.oauth.service;

import com.app.external.oauth.model.OAuthAttributes;

public interface SocialLoginApiService {
    // 소셜 플랫폼에서 회원 정보를 불러옴
    OAuthAttributes getUserInfo(String accessToken);
}
