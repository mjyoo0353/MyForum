package com.mj.myforum.controller;

import com.mj.myforum.domain.Post;
import com.mj.myforum.domain.User;
import com.mj.myforum.form.CommentForm;
import com.mj.myforum.service.CommentService;
import com.mj.myforum.service.PostService;
import com.mj.myforum.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@RequiredArgsConstructor
@Controller
public class CommentController {

    private final UserService userService;
    private final PostService postService;
    private final CommentService commentService;

    @PostMapping("/posts/{postId}")
    public String createComment(@Validated CommentForm commentForm, BindingResult bindingResult,
                                @PathVariable("postId") Long postId, Model model,
                                @SessionAttribute(name = "loginUser", required = false) User loginUser,
                                RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("postId", postId);
            return "redirect:/posts/{postId}";
        }

        Post post = postService.getPost(postId);
        User user = userService.findById(loginUser.getId());

        commentService.save(post, user, commentForm.getContent());
        redirectAttributes.addFlashAttribute("postId", postId);
        return "redirect:/posts/{postId}";
    }

}
