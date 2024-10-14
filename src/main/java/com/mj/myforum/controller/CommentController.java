package com.mj.myforum.controller;

import com.mj.myforum.domain.Comment;
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


@Controller
@RequiredArgsConstructor
@RequestMapping("/posts/{postId}")
public class CommentController {

    private final UserService userService;
    private final PostService postService;
    private final CommentService commentService;

    @PostMapping("/comments")
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

    @GetMapping("comments/edit/{commentId}")
    public String editComment(@ModelAttribute("commentForm") CommentForm commentForm,
                              @PathVariable("commentId") Long commentId) {

        Comment comment = commentService.getComment(commentId);
        commentForm.setContent(comment.getContent());

        return "comments/editComment";
    }

    @PostMapping("comments/edit/{commentId}")
    public String editComment(@Validated CommentForm commentForm, BindingResult bindingResult,
                                @PathVariable("commentId") Long commentId) {

        if (bindingResult.hasErrors()) {
            return "commentForm";
        }
        Comment comment = commentService.getComment(commentId);
        commentService.update(comment, commentForm.getContent());
        return "redirect:/posts/{postId}";
    }

    @GetMapping("comments/delete/{commentId}")
    public String deleteComment(@PathVariable("commentId") Long commentId,
                                RedirectAttributes redirectAttributes) {
        Comment comment = commentService.getComment(commentId);
        commentService.delete(comment);

        return "redirect:/posts/{postId}";
    }
}
