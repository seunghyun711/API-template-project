package com.app.domain.member.repository;

import com.app.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);

    // 리프레시 토큰으로 회원 찾기
    Optional<Member> findByRefreshToken(String refreshToken);
}
