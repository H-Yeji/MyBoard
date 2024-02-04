package com.yjstudy.myboard.web.controller;

import com.yjstudy.myboard.domain.Member;
import com.yjstudy.myboard.service.MemberService;
import com.yjstudy.myboard.web.form.AddMemberForm;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    /**
     * 회원 등록 폼 열어봄
     */
    @GetMapping("/members/new")
    public String createForm(Model model) {

        model.addAttribute("addMemberForm", new AddMemberForm());
        return "members/createMemberForm";
    }

    /**
     * 회원 가입 진행
     */
    @PostMapping("/members/new")
    public String create(@Validated @ModelAttribute AddMemberForm addMemberForm, BindingResult result, Model model) {

        if (result.hasErrors()) {
            log.info("errors={}", result);
            return "members/createMemberForm";
        }

        Member member = new Member();
        member.setLoginId(addMemberForm.getLoginId());
        member.setPassword(addMemberForm.getPassword());
        member.setUsername(addMemberForm.getUsername());
        member.setEmail(addMemberForm.getEmail());

        memberService.join(member);

        model.addAttribute("message", "회원가입이 완료되었습니다.");
        model.addAttribute("searchUrl", "/");

        return "message";
    }
}
