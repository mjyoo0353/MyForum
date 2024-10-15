package com.mj.myforum.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PostForm {

    @NotBlank(message = "Enter a title")
    @Size(max = 50, message = "제목은 50자 미만으로 작성하세요.")
    private String title;


    @NotBlank(message = "Enter a content")
    @Size(max = 1000, message = "내용은 1000자 미만으로 작성하세요.")
    private String content;
}
