package com.yjstudy.myboard.web.controller;

import com.yjstudy.myboard.domain.Board;
import com.yjstudy.myboard.repository.BoardRepository;
import com.yjstudy.myboard.service.BoardService;
import com.yjstudy.myboard.web.form.BoardForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BoardController {

    private final BoardService boardService;

    /**
     * 게시물 등록 폼 열기
     */
    @GetMapping("/boards/new")
    public String createPostForm(Model model) {

        model.addAttribute("boardForm", new BoardForm());
        return "boards/createBoardForm";
    }
    /**
     * 게시물 등록
     */
    @PostMapping("/boards/new")
    public String registerPost(Board board) {

        boardService.addPost(board);
        return "redirect:/boards"; //게시글 목록 페이지로 리다이렉트
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
    public String boardDelete(@PathVariable int id) {

        boardService.delete(id);

        return "redirect:/boards";
    }

    /**
     * 상품 수정 폼
     */
    @GetMapping("/boards/{id}/edit")
    public String boardEditForm(@PathVariable int id, Model model) {

        Board board = boardService.detail(id); //id로 게시물 찾아옴

        BoardForm form = new BoardForm();
        form.setId(board.getId());
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
    public String boardEdit(@PathVariable int id, @ModelAttribute("boardForm") BoardForm boardForm) {

        boardService.update(id, boardForm.getTitle(), boardForm.getContent());

        return "redirect:/boards";
    }
}
