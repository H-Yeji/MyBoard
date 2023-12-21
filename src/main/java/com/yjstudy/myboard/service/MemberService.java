package com.yjstudy.myboard.service;

import com.yjstudy.myboard.domain.Member;
import com.yjstudy.myboard.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 회원 가입
     */
    @Transactional
    public String join(Member member) {
        //중복 회원 검증
        validateDuplicateMember(member.getLoginId());
        //검증을 통과하면 회원 저장
        memberRepository.save(member);
        //id 반환
        return member.getLoginId();
    }

    private void validateDuplicateMember(String loginId) { //loginId로 중복검사

        List<Member> findMembers = memberRepository.findByLoginId(loginId);
        if (!findMembers.isEmpty()) { //비어있지 않으면 = 이미 아이디가 존재하면
            throw new IllegalStateException("이미 존재하는 아이디입니다.");
        }
    }

    /**
     * 회원 전체 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /**
     * 회원 한명 조회
     */
    public Member findOne(String loginId) {
        return memberRepository.findOne(loginId);
    }
}
