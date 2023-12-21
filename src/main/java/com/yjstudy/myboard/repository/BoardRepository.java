package com.yjstudy.myboard.repository;

import com.yjstudy.myboard.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer> {

    //Page<Board> findByTitleContaining(String searchKeyword, Pageable pageable);
}
