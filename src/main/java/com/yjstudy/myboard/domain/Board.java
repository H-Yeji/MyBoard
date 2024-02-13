package com.yjstudy.myboard.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Board {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String writer;

    private String title;

    private String content;

    private LocalDateTime createdDateTime;
}
