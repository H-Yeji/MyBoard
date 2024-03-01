package com.yjstudy.myboard.repository;

import com.yjstudy.myboard.domain.UploadFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UploadFileRepository extends JpaRepository<UploadFile, Integer> {

}
