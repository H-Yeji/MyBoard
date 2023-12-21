package com.yjstudy.myboard.service;

import com.yjstudy.myboard.domain.Board;
import com.yjstudy.myboard.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    /**
     * 게시물 등록
     */
    public void addPost(Board board) {

        boardRepository.save(board);
    }

    /**
     * 게시글 리스트
     */
    public List<Board> boardList() {

        return boardRepository.findAll();
    }
    /*public Page<Board> boardList (Pageable pageable) {

        return boardRepository.findAll(pageable);
    }*/

    /**
     * 게시글 상세보기
     */
    public Board detail(int idx) {

        return boardRepository.findById(idx).orElse(null);
    }

    /**
     * 게시글 수정 -> 추후 변경
     */
    public void update(Board board) {

        boardRepository.save(board);
    }

    /**
     * 게시글 삭제
     */
    public void delete(int idx) {

        boardRepository.deleteById(idx);
    }

}
