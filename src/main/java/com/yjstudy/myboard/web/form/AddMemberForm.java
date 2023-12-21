package com.yjstudy.myboard.web.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AddMemberForm {

    @NotBlank(message = "이름은 필수입니다.")
    private String username;

    @NotBlank(message = "아이디는 필수입니다.")
    private String loginId;

    @NotBlank(message = "비밀번호는 필수입니다.")
    private String password;

    @Email
    private String email;
}
