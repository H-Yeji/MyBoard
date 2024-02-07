package com.yjstudy.myboard;

import com.yjstudy.myboard.domain.Member;
import com.yjstudy.myboard.repository.MemberRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final MemberRepository memberRepository;

    /**
     * 테스트용 데이터 추가
     */
    @Transactional
    @EventListener(ApplicationReadyEvent.class)
    public void init() {

        Member memberA = new Member();
        memberA.setLoginId("membera");
        memberA.setPassword("aaaaa11111.");
        memberA.setUsername("a");

        Member memberB = new Member();
        memberB.setLoginId("memberb");
        memberB.setPassword("aaaaa11111.");
        memberB.setUsername("b");

        memberRepository.save(memberA);
        memberRepository.save(memberB);

    }
}
