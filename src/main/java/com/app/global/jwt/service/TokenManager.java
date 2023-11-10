package com.app.global.jwt.service;

import com.app.domain.member.constant.Role;
import com.app.global.error.ErrorCode;
import com.app.global.error.exception.AuthenticationException;
import com.app.global.jwt.constant.GrantType;
import com.app.global.jwt.constant.TokenType;
import com.app.global.jwt.dto.JwtTokenDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Slf4j
@RequiredArgsConstructor
public class TokenManager { // 토큰 관리 클래스
    private final String accessTokenExpirationTime;
    private final String refreshTokenExpirationTime;
    private final String tokenSecret;

    // jwtTokenDto 생성하는 메서드
    public JwtTokenDto createJwtTokenDto(Long memberId, Role role) {
        Date accessTokenExpireTime = createAccessTokenExpireTime();
        Date refreshTokenExpireTime = createRefreshTokenExpireTime();

        String accessToken = createAccessToken(memberId, role, accessTokenExpireTime); // 액세스 토큰
        String refreshToken = createRefreshToken(memberId, refreshTokenExpireTime); // 리프레시 토큰

        return JwtTokenDto.builder()
                .grantType(GrantType.BEARER.getType())
                .accessToken(accessToken)
                .accessTokenExpireTime(accessTokenExpireTime)
                .refreshToken(refreshToken)
                .refreshTokenExpireTime(refreshTokenExpireTime)
                .build();
    }

    // 액세스 토큰 만료시간 반환하는 메서드
    public Date createAccessTokenExpireTime() {
        return new Date(System.currentTimeMillis() + Long.parseLong(accessTokenExpirationTime));
    }

    // 리프레시 토큰 만료시간 반환하는 메서드
    public Date createRefreshTokenExpireTime(){
        return new Date(System.currentTimeMillis() + Long.parseLong(refreshTokenExpirationTime));
    }

    // 액세스 토큰 생성하는 메서드
    public String createAccessToken(Long memberId, Role role, Date expirationTime) {
        String accessToken = Jwts.builder()
                .setSubject(TokenType.ACCESS.name()) // 토큰 제목
                .setIssuedAt(new Date()) // 토큰이 발급된 시간
                .setExpiration(expirationTime) // 토큰이 만료된 시간
                .claim("memberId", memberId) // 회원 아이디
                .claim("role", role) // 유저 role
                .signWith(SignatureAlgorithm.HS512, tokenSecret.getBytes(StandardCharsets.UTF_8)) // 토큰 생성할 때 사용할 알고리즘 지정
                .setHeaderParam("typ", "JWT")
                .compact();
        return accessToken;
    }

    // 리프레시 토큰 생성하는 메서드
    public String createRefreshToken(Long memberId, Date expirationTime) {
        String refreshToken = Jwts.builder()
                .setSubject(TokenType.REFRESH.name()) // 토큰 제목
                .setIssuedAt(new Date()) // 토큰이 발급된 시간
                .setExpiration(expirationTime) // 토큰이 만료된 시간
                .claim("memberId", memberId) // 회원 아이디
                .signWith(SignatureAlgorithm.HS512, tokenSecret.getBytes(StandardCharsets.UTF_8)) // 토큰 생성할 때 사용할 알고리즘 지정
                .setHeaderParam("typ", "JWT")
                .compact();
        return refreshToken;
    }

    // 유효한 토큰인지 검증하는 메서드
    public void validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(tokenSecret.getBytes(StandardCharsets.UTF_8))
                    .parseClaimsJws(token);
        } catch (ExpiredJwtException e) { // 토큰 만료 시 발생하는 예외
            log.info("token 만료", e);
            throw new AuthenticationException(ErrorCode.TOKEN_EXPIRED);
        } catch (Exception e) {
            log.info("유효하지 않은 token : ", e);
            throw new AuthenticationException(ErrorCode.NOT_VALID_TOKEN);
        }
    }

    // 토큰 정보를 서버에서 사용할 때 페이로드에 있는 정보를 가져와야 하는데 이때 페이로드에 있는 claim 정보를 가져오는 메서드
    public Claims getTokenClaims(String token) {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(tokenSecret.getBytes(StandardCharsets.UTF_8))
                    .parseClaimsJws(token).getBody(); // 페이로드에 담겨있는 key-value 한 쌍(페이로드)를 가져올 수 있다.
        } catch (Exception e) {
            log.info("유효하지 않은 token : ", e);
            throw new AuthenticationException(ErrorCode.NOT_VALID_TOKEN);
        }
        return claims;
    }
}
