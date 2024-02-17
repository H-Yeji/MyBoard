package com.yjstudy.myboard.web.controller;

import com.yjstudy.myboard.domain.Board;
import com.yjstudy.myboard.domain.Comment;
import com.yjstudy.myboard.domain.Member;
import com.yjstudy.myboard.service.BoardService;
import com.yjstudy.myboard.service.CommentService;
import com.yjstudy.myboard.service.MemberService;
import com.yjstudy.myboard.web.form.CommentForm;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static com.yjstudy.myboard.web.SessionConst.LOGIN_MEMBER;

@Slf4j
@Controller
@RequiredArgsConstructor
public class CommentController {

    private final BoardService boardService;
    private final MemberService memberService;
    private final CommentService commentService;

    @PostMapping("/boards/{id}/comment") //boardId
    public String addComment(@PathVariable int id, @ModelAttribute("commentForm") CommentForm form,
                             HttpServletRequest request, RedirectAttributes redirectAttributes) {

        //id로 board찾아오기
        Board board = boardService.detail(id);

        HttpSession session = request.getSession(false);
        Member loginMember = (Member) session.getAttribute(LOGIN_MEMBER);


        Comment comment = Comment.builder()
                .content(form.getContent())
                .member(loginMember)
                .build();

        //comment 저장
        commentService.writeComment(comment, board.getId(), loginMember.getLoginId());

        redirectAttributes.addAttribute("boardId", id);

        return "redirect:/boards/{boardId}";
    }
}
