package com.yjstudy.myboard.web.form;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class BoardForm {

    @NotNull
    private String writer;

    private String title;

    private String content;

    private LocalDateTime createdDateTime;
}
