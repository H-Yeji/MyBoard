package com.yjstudy.myboard.service;

import com.yjstudy.myboard.domain.Board;
import com.yjstudy.myboard.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class BoardService {

    private final BoardRepository boardRepository;

    /**
     * 게시물 등록
     */
    @Transactional
    public void addPost(Board board) {

        boardRepository.save(board);
    }

    /**
     * 게시글 리스트
     */
    public List<Board> boardList() {

        return boardRepository.findAll();
    }

    /**
     * 게시글 상세보기
     */
    public Board detail(int idx) {

        return boardRepository.findById(idx).orElse(null);
    }

    /**
     * 게시글 수정 -> 변경 감지 기능 사용
     */
    @Transactional
    public void update(int id, String title, String content) {

        Optional<Board> board = boardRepository.findById(id);

        if (board.isPresent()) {
            Board findPost = board.get();
            findPost.setTitle(title);
            findPost.setContent(content);
        }
        else {
            log.warn("Board with id {} not found", id);
        }
    }

    /**
     * 게시글 삭제
     */
    @Transactional
    public void delete(int idx) {

        boardRepository.deleteById(idx);
    }

}
