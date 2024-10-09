package com.mj.myforum.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SignupForm {

    @NotBlank(message = "로그인 ID는 필수입니다.")
    private String loginId;

    @NotBlank(message = "비밀번호는 필수입니다.")
    private String password;

    @NotBlank
    private String passwordCheck;

    @NotBlank(message = "이름은 필수입니다.")
    private String name;

    @Email @NotBlank(message = "이메일은 필수입니다.")
    private String email;

}
