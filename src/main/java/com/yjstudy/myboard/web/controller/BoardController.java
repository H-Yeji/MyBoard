package com.yjstudy.myboard.web.controller;

import com.yjstudy.myboard.domain.Board;
import com.yjstudy.myboard.repository.BoardRepository;
import com.yjstudy.myboard.service.BoardService;
import com.yjstudy.myboard.web.form.BoardForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardRepository boardRepository;
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
        return "redirect:/boards/list"; //게시글 목록 페이지로 리다이렉트
    }

    /**
     * 게시글 목록
     */
    @GetMapping("/boards/list")
    public String boardList(Model model) {

        model.addAttribute("boardList", boardService.boardList());
        return "boards/boardList";
    }

    /**
     * 게시글 상세보기
     */
    @GetMapping("/boards/list/{id}")
    public String boardView(@PathVariable int id, Model model) {

        Optional<Board> board = boardRepository.findById(id);
        model.addAttribute("board", board);

        return "boards/boardView";
    }
}
