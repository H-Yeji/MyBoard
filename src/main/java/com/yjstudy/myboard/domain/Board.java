package com.yjstudy.myboard.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Board {

    @Id @GeneratedValue
    private Long id;

    @NotBlank
    private String writer;

    private String title;

    private String content;
}
