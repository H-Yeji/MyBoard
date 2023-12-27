package com.yjstudy.myboard.web.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AddMemberForm {

    @NotBlank(message = "이름은 필수입니다.")
    private String username;

    @NotBlank(message = "아이디는 필수입니다.")
    @Pattern(regexp = "^[a-z0-9]{2,10}$", message = "아이디는 특수문자를 제외한 2~10자리여야 합니다.")
    private String loginId;

    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}",
            message = "비밀번호는 8~16자 영문 대소문자, 숫자, 특수문자를 사용하세요.")
    @NotBlank(message = "비밀번호는 필수입니다.")
    private String password;

    @Email
    private String email;
}
