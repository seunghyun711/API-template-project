package com.app.external.oauth.model;

import com.app.domain.member.constant.MemberType;
import com.app.domain.member.constant.Role;
import com.app.domain.member.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

// 소셜 플랫폼에서 회원정보를 가져오면 반환형태가 다르다. 이걸 하나로 통일해서 해당 클래스에 있는 회원 정보를 통해 회원가입 진행하기 위한 작업을 진행한다.
@Getter
@Builder
@ToString
public class OAuthAttributes {
    private String name;
    private String email;
    private String profile;
    private MemberType memberType;

    public Member toMemberEntity(MemberType memberType, Role role) {
        return Member.builder()
                .memberName(name)
                .email(email)
                .profile(profile)
                .role(role)
                .build();
    }

}
