package com.yjstudy.myboard.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Board {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String writer;

    private String title;

    private String content;
}
