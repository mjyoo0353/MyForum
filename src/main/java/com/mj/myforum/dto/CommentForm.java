package com.mj.myforum.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CommentForm {

    @NotBlank(message = "Enter a content")
    private String content;
}
