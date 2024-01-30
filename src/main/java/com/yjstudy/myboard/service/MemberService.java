package com.yjstudy.myboard.service;

import com.yjstudy.myboard.domain.Member;
import com.yjstudy.myboard.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
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

        log.info("member.getLoginId = {}", member.getLoginId());
        //중복 회원 검증
        validateDuplicateMember(member.getLoginId());

        //검증을 통과하면 회원 저장
        memberRepository.save(member);
        //id 반환
        return member.getLoginId();
    }

    private void validateDuplicateMember(String loginId) { //loginId로 중복검사

        log.info("중복검사 시작");
        Member findMember = memberRepository.findByLoginId(loginId);
        log.info("중복검사 끝");

        if (findMember != null) { //비어있지 않으면 = 이미 아이디가 존재하면
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
    public Member findOne(Long id) {
        return memberRepository.findOne(id);
    }
}
