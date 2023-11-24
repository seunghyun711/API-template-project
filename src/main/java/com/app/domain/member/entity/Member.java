package com.app.domain.member.entity;

import com.app.domain.common.BaseEntity;
import com.app.domain.member.constant.MemberType;
import com.app.domain.member.constant.Role;
import com.app.global.jwt.dto.JwtTokenDto;
import com.app.global.util.DateTimeUtils;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
// Member 엔티티 클래스
// Member 엔티티 클래스에 있는 필드값이 무분별하게 변경되는 것을 막기 위해 @Setter를 사용하지 않고 필요한 필드만 setter 클래스 생성
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본 생성자 생성하는 애너테이션
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // id 생성 전략을 db에 위임
    private Long memberId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10) // length의 기본값은 259
    private MemberType memberType;

    @Column(unique = true, length = 50, nullable = false)
    private String email; // 이메일

    @Column(length = 200)
    private String password; // 폼 로그인 방식 적용 시 필요한 필드

    @Column(nullable = false, length = 20)
    private String memberName; // 회원 이름

    @Column(length = 200)
    private String profile; // 프로필 이미지

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private Role role; // 회원 등급(일반 유저, 관리자)

    @Column(length = 250)
    private String refreshToken; // 리프레시 토큰

    private LocalDateTime tokenExpirationTime; // 리프레시 토큰 만료시간

    @Builder
    public Member(MemberType memberType, String email, String password, String memberName, String profile, Role role) {
        this.memberType = memberType;
        this.email = email;
        this.password = password;
        this.memberName = memberName;
        this.profile = profile;
        this.role = role;
    }

    public void updateRefreshToken(JwtTokenDto jwtTokenDto) {
        this.refreshToken = jwtTokenDto.getRefreshToken();
        this.tokenExpirationTime = DateTimeUtils.convertToLocalDateTime(jwtTokenDto.getRefreshTokenExpireTime());
    }

    public void setMemberType(MemberType memberType) {
        this.memberType = memberType;
    }

    // 토큰 만료 시간을 현재시간으로 설정
    public void expireRefreshToken(LocalDateTime now) {
        this.tokenExpirationTime = now;
    }
}
