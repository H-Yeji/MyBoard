package com.yjstudy.myboard;

import com.yjstudy.myboard.domain.Board;
import com.yjstudy.myboard.domain.Member;
import com.yjstudy.myboard.repository.BoardRepository;
import com.yjstudy.myboard.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    /**
     * 테스트용 데이터 추가
     */
    @Transactional
    @EventListener(ApplicationReadyEvent.class)
    public void init() {

        // 회원
        Member memberA = new Member();
        memberA.setLoginId("aa");
        memberA.setPassword("aaaaa11111.");
        memberA.setUsername("첫번째회원");
        memberA.setEmail("aaa@naver.com");

        Member memberB = new Member();
        memberB.setLoginId("bb");
        memberB.setPassword("aaaaa11111.");
        memberB.setUsername("두번째회원");
        memberB.setEmail("bbb@naver.com");

        memberRepository.save(memberA);
        memberRepository.save(memberB);

        // 게시글
        Board boardA = new Board();
        boardA.setWriter("aa");
        boardA.setTitle("hello");
        boardA.setContent("hello");
        boardA.setCreatedDateTime(LocalDateTime.now());

        boardRepository.save(boardA);

    }
}
