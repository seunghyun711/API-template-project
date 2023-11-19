package com.app.domain.member.service;

import com.app.domain.member.entity.Member;
import com.app.domain.member.repository.MemberRepository;
import com.app.global.error.ErrorCode;
import com.app.global.error.exception.AuthenticationException;
import com.app.global.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    // 회원 등록
    public Member registerMember(Member member) {
        validateDuplicateMember(member);
        return memberRepository.save(member);
    }

    // 중복 이메일 여부 판별하는 메서드
    private void validateDuplicateMember(Member member) {
        Optional<Member> optionalMember = memberRepository.findByEmail(member.getEmail());
        if (optionalMember.isPresent()) {
            throw new BusinessException(ErrorCode.ALREADY_REGISTERED_MEMBER);
        }
    }

    @Transactional(readOnly = true) // 읽기 기능만 사용
    public Optional<Member> findMemberByEmail(String email) {
        return memberRepository.findByEmail(email);
    }

    @Transactional(readOnly = true) // 읽기 기능만 사용
    public Member findMemberByRefreshToken(String refreshToken) { // 리프레시 토큰으로 회원 찾기
        Member member = memberRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new AuthenticationException(ErrorCode.REFRESH_TOKEN_NOT_FOUND));
        LocalDateTime tokenExpirationTime = member.getTokenExpirationTime();
        if(tokenExpirationTime.isBefore(LocalDateTime.now())) { // 리프레시 토큰의 만료시간이 현재 시간보다 이전이면(리프레시 토큰이 만료된 경우)예외처리
            throw new AuthenticationException(ErrorCode.REFRESH_TOKEN_EXPIRED);
        }
        return member;
    }
}
