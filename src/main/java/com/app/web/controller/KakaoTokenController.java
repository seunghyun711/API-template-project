package com.app.web.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class KakaoTokenController {
    @GetMapping("/login") // 카카오 로그인
    public String login() {
        return "loginForm";
    }

    @GetMapping("/oauth/kakao/callback") // Authorization code 발급
    public @ResponseBody String loginCallback(String code) {
        return code;
    }
}
