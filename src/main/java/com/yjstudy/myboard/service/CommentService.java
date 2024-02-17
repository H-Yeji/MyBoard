package com.yjstudy.myboard.service;

import com.yjstudy.myboard.domain.Board;
import com.yjstudy.myboard.domain.Comment;
import com.yjstudy.myboard.domain.Member;
import com.yjstudy.myboard.repository.BoardRepository;
import com.yjstudy.myboard.repository.CommentRepository;
import com.yjstudy.myboard.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

    /**
     * 댓글 작성
     */
    public Long writeComment(Comment comment, int boardId, String loginId) {

        //회원 찾아오기
        Member member = memberRepository.findByLoginId(loginId);
        //게시물 찾아오기
        Optional<Board> boardOptional = boardRepository.findById(boardId);
        Board board = boardOptional.orElseThrow(() -> new RuntimeException("Board not found"));

        Comment result = Comment.builder()
                .content(comment.getContent())
                .board(board)
                .member(member)
                .build();

        commentRepository.save(result);
        return result.getId();
    }

    /**
     * board id로 게시물 찾아서 해당 댓글 목록 가져오기
     */
    public List<Comment> commentList(int boardId) {

        Optional<Board> boardOptional = boardRepository.findById(boardId);
        Board board = boardOptional.orElseThrow(() -> new RuntimeException("Board not found"));

        //board로 댓글 목록 가져오기
        List<Comment> comments = commentRepository.findByBoard(board);

        return comments;
    }


}
