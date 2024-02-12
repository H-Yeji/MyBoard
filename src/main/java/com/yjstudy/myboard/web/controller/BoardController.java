package com.yjstudy.myboard.web.controller;

import com.yjstudy.myboard.domain.Board;
import com.yjstudy.myboard.domain.Member;
import com.yjstudy.myboard.service.BoardService;
import com.yjstudy.myboard.service.MemberService;
import com.yjstudy.myboard.web.form.BoardForm;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import static com.yjstudy.myboard.web.SessionConst.LOGIN_MEMBER;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BoardController {

    private final BoardService boardService;

    /**
     * 게시글 등록 폼 열기
     */
    @GetMapping("/boards/new")
    public String createPostForm(Model model, HttpServletRequest request) {

        BoardForm boardForm = new BoardForm();

        HttpSession session = request.getSession(false);
        Member loginMember = (Member) session.getAttribute(LOGIN_MEMBER);

        if (loginMember != null) {
            boardForm.setWriter(loginMember.getLoginId()); //session에서 찾아온 회원id를 boardForm에 뿌리기
            log.info("작성자 : ", loginMember.getLoginId());
        }

        model.addAttribute("boardForm", boardForm);

        return "boards/createBoardForm";
    }
    /**
     * 게시글 등록
     */
    @PostMapping("/boards/new")
    public String registerPost(@ModelAttribute("boardForm") BoardForm form, RedirectAttributes redirectAttributes) {

        Board board = new Board();
        board.setWriter(form.getWriter());
        board.setTitle(form.getTitle());
        board.setContent(form.getContent());

        boardService.addPost(board);

        redirectAttributes.addAttribute("boardId", board.getId());
        redirectAttributes.addFlashAttribute("result", "registerOK");

        return "redirect:/boards/{boardId}";
    }

    /**
     * 게시글 목록
     */
    @GetMapping("/boards")
    public String boardList(Model model, HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        Member loginMember = (Member) session.getAttribute(LOGIN_MEMBER);

        model.addAttribute("member", loginMember);
        model.addAttribute("boardList", boardService.boardList());
        return "boards/boardList";
    }

    /**
     * 게시글 상세보기
     */
    @GetMapping("/boards/{id}")
    public String boardView(@PathVariable int id, Model model, HttpServletRequest request) {

        Board board = boardService.detail(id); //해당 id의 게시글 찾기

        HttpSession session = request.getSession(false);
        Member loginMember = (Member) session.getAttribute(LOGIN_MEMBER);

        model.addAttribute("board", board);
        model.addAttribute("member", loginMember);

        return "boards/boardView";
    }

    /**
     * 게시글 삭제
     */
    @GetMapping("/boards/{id}/delete")
    public String boardDelete(@PathVariable int id, Model model) {

        boardService.delete(id);

        model.addAttribute("message", "글이 삭제되었습니다.");
        model.addAttribute("searchUrl", "/boards");

        return "message";
    }

    /**
     * 게시글 수정 폼
     */
    @GetMapping("/boards/{id}/edit")
    public String boardEditForm(@PathVariable int id, Model model) {

        Board board = boardService.detail(id); //id로 게시물 찾아옴

        BoardForm form = new BoardForm();
        form.setWriter(board.getWriter());
        form.setTitle(board.getTitle());
        form.setContent(board.getContent());

        model.addAttribute("boardForm", form);
        return "boards/updateBoardForm";
    }

    /**
     * 게시글 수정 진행
     */
    @PostMapping("/boards/{id}/edit")
    public String boardEdit(@PathVariable int id, @ModelAttribute("boardForm") BoardForm boardForm,
                            RedirectAttributes redirectAttributes) {

        boardService.update(id, boardForm.getTitle(), boardForm.getContent());

        redirectAttributes.addAttribute("boardId", id);
        redirectAttributes.addFlashAttribute("result", "modifyOK");

        return "redirect:/boards/{boardId}";
    }
}
