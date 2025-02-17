package com.yjstudy.myboard.repository;

import com.yjstudy.myboard.domain.Board;
import com.yjstudy.myboard.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByBoard(Board board);
}
