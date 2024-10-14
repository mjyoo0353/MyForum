package com.mj.myforum.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CommentForm {

    @NotBlank(message = "내용을 입력해주세요.")
    private String content;
}
