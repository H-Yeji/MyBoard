package com.yjstudy.myboard.web.controller;

import com.yjstudy.myboard.domain.Board;
import com.yjstudy.myboard.repository.BoardRepository;
import com.yjstudy.myboard.service.BoardService;
import com.yjstudy.myboard.web.form.BoardForm;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BoardController {

    private final BoardService boardService;

    /**
     * 게시글 등록 폼 열기
     */
    @GetMapping("/boards/new")
    public String createPostForm(Model model, HttpSession session) {

        BoardForm boardForm = new BoardForm();

        //사용자 컨텍스트에서 로그인한 사용자의 loginId 검색
        String loggedInUserId = (String) session.getAttribute("loggedInUserId");

        if (loggedInUserId != null) {
            boardForm.setWriter(loggedInUserId); //session에서 찾아온 회원id를 boardForm에 뿌리기
            log.info("loggedInUserId in createPostForm: {}", loggedInUserId);
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
    public String boardList(Model model) {

        model.addAttribute("boardList", boardService.boardList());
        return "boards/boardList";
    }

    /**
     * 게시글 상세보기
     */
    @GetMapping("/boards/{id}")
    public String boardView(@PathVariable int id, Model model) {

        Board board = boardService.detail(id);

        model.addAttribute("board", board);

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
