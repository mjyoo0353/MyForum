package com.mj.myforum.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SignupForm {

    @NotBlank(message = "ID를 입력해주세요.")
    private String loginId;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;

    @NotBlank(message = "Confirm password")
    private String passwordCheck;

    @NotBlank(message = "이름을 입력해주세요.")
    private String name;

    @Email @NotBlank(message = "이메일을 입력해주세요.")
    private String email;

}
