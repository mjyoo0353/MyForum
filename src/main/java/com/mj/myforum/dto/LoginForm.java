package com.mj.myforum.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginForm {

    @NotBlank(message = "Enter your Login ID")
    private String loginId;

    @NotBlank(message = "Enter your password")
    private String password;
}
