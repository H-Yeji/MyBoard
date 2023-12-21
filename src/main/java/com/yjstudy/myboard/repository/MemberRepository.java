package com.yjstudy.myboard.repository;

import com.yjstudy.myboard.domain.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository //컴포넌트 스캔에서 자동으로 스프링 빈으로 등록
@RequiredArgsConstructor
@Slf4j
public class MemberRepository {

    private final EntityManager em;

    /**
     * 저장
     */
    public void save(Member member) {
        log.info("persist 성공");
        em.persist(member);
    }

    /**
     * 조회
     */
    public Member findOne(String loginId) {
        return em.find(Member.class, loginId);
    }

    public List<Member> findAll() {

        List<Member> result = em.createQuery("select m from Member m", Member.class)
                .getResultList();

        return result;
    }

    public List<Member> findByLoginId(String loginId) {

        List<Member> result = em.createQuery("select m from Member m where m.loginId = :loginId", Member.class)
                .setParameter("loginId", loginId)
                .getResultList();

        return result;
    }
}
