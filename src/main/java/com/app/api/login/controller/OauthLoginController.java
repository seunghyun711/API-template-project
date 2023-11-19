package com.app.api.login.controller;

import com.app.api.login.dto.OauthLoginDto;
import com.app.api.login.service.OauthLoginService;
import com.app.api.login.validator.OauthValidator;
import com.app.domain.member.constant.MemberType;
import com.app.global.util.AuthorizationHeaderUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/oauth")
@Slf4j
public class OauthLoginController {

    private final OauthValidator oauthValidator;
    private final OauthLoginService oauthLoginService;
    @PostMapping("/login")
    public ResponseEntity<OauthLoginDto.Response> oauthLogin(@RequestBody OauthLoginDto.Request dto,
                                                             HttpServletRequest httpServletRequest) {
        String authorizationHeader = httpServletRequest.getHeader("Authorization");
        AuthorizationHeaderUtils.validateAuthorization(authorizationHeader);
        oauthValidator.validateMemberType(dto.getMemberType());
        log.info("dto.getMemberType : {}", dto.getMemberType());

        String accessToken = authorizationHeader.split(" ")[1];

        OauthLoginDto.Response jwtTokenResponse =
                oauthLoginService.oauthLogin(accessToken, MemberType.from(dto.getMemberType()));

        return ResponseEntity.ok(jwtTokenResponse);
    }
}
