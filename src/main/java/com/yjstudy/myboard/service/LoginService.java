package com.yjstudy.myboard.service;

import com.yjstudy.myboard.domain.Member;
import com.yjstudy.myboard.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;

    /**
     * 로그인
     */
    public Member login(String loginId, String password) {

        Member findMember = memberRepository.findByLoginId(loginId);
        if (findMember != null && findMember.getPassword().equals(password)) {
            return findMember;
        } else {
            return null;
        }
    }


}
