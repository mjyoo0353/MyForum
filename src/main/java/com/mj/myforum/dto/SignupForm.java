package com.mj.myforum.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupForm {

    @NotBlank(message = "Enter a login ID")
    private String loginId;

    @NotBlank(message = "Enter a password")
    private String password;

    @NotBlank(message = "Type your password again")
    private String passwordCheck;

    @NotBlank(message = "Enter your name")
    private String username;

    @Email @NotBlank(message = "Enter your email")
    private String email;

}
