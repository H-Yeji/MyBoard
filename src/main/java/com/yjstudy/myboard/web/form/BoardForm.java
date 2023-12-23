package com.yjstudy.myboard.web.form;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BoardForm {

    @NotBlank
    private String writer; //추후에 loginId를 받아오는 것으로 변경

    private String title;

    private String content;
}
