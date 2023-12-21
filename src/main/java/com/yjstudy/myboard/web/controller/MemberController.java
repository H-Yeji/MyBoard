package com.yjstudy.myboard.web.controller;

import com.yjstudy.myboard.domain.Member;
import com.yjstudy.myboard.service.MemberService;
import com.yjstudy.myboard.web.form.AddMemberForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
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
    public String create(@Valid AddMemberForm memberForm, BindingResult result) {

        if (result.hasErrors()) {
            return "members/createMemberForm";
        }

        Member member = new Member();
        member.setLoginId(memberForm.getLoginId());
        member.setPassword(memberForm.getPassword());
        member.setUsername(memberForm.getUsername());
        member.setEmail(memberForm.getEmail());

        memberService.join(member);
        return "redirect:/boards/list"; // 첫번째 페이지로 돌아감
    }
}
