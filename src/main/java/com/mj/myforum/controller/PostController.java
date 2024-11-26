package com.mj.myforum.controller;

import com.mj.myforum.dto.CommentForm;
import com.mj.myforum.dto.PostRequestDto;
import com.mj.myforum.dto.PostResponseDto;
import com.mj.myforum.entity.Comment;
import com.mj.myforum.entity.Post;
import com.mj.myforum.entity.User;
import com.mj.myforum.service.CommentService;
import com.mj.myforum.service.LikeService;
import com.mj.myforum.service.PostService;
import com.mj.myforum.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/posts")
@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final UserService userService;
    private final CommentService commentService;
    private final LikeService likeService;

    @GetMapping("/list")
    public String postList(@RequestParam(value = "keyword", required = false) String keyword,
                           @RequestParam(value = "page", required = false, defaultValue = "0") int page,
                           Model model) {

        Page<Post> postList = postService.getPosts(keyword, page);
        model.addAttribute("postList", postList);
        return "posts/postList";
    }

    //작성한 글 보기
    @GetMapping("/{postId}")
    public String viewPost(@PathVariable Long postId, Model model,
                           @ModelAttribute CommentForm commentForm,
                           @SessionAttribute(name = "loginUser", required = false) User loginUser) {

        Post post = postService.findById(postId);
        List<Comment> comments = commentService.commentList(postId);

        model.addAttribute("post", post);
        model.addAttribute("loginUser", loginUser);
        model.addAttribute("commentForm", commentForm);
        model.addAttribute("comments", comments);
        return "posts/getPost";
    }

    //게시글 작성 폼 불러오기
    @GetMapping("/create")
    public String createPostForm(@ModelAttribute("form") PostRequestDto form) {
        return "posts/createPost";
    }

    //게시글 작성
    @PostMapping("/create")
    public String createPost(@Validated @ModelAttribute("form") PostRequestDto form, BindingResult bindingResult,
                             @SessionAttribute(name = "loginUser") User loginUser,
                             RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "posts/createPost";
        }
        User user = userService.findById(loginUser.getId());
        Long postId = postService.save(user.getId(), form.getTitle(), form.getContent());
        redirectAttributes.addAttribute("postId", postId);
        return "redirect:/posts/{postId}";
    }

    //게시글 수정하기
    @GetMapping("/edit")
    public String editPostForm(@ModelAttribute("form") PostRequestDto form,
                               @RequestParam("postId") Long postId,
                               Model model) {

        Post post = postService.findById(postId); //수정할 post 객체 불러오기
        PostRequestDto postRequestDto = PostRequestDto.fromEntity(post);
        model.addAttribute("form", postRequestDto); //해당 form 뷰에 전달
        model.addAttribute("postId", postId); //수정할 id도 전달*/
        return "posts/editPost";
    }

    @PostMapping("/edit/{postId}")
    public String editPost(@Validated @ModelAttribute("form") PostRequestDto form, BindingResult bindingResult,
                           @PathVariable Long postId, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "posts/editPost";
        }
        Post post = postService.findById(postId);
        postService.update(post, form.getTitle(), form.getContent());
        redirectAttributes.addAttribute("postId", postId);
        return "redirect:/posts/{postId}";
    }

    //게시글 삭제하기
    @GetMapping("/delete")
    public String deletePost(@RequestParam("postId") Long postId) {
        Post post = postService.findById(postId);
        postService.delete(post);
        return "redirect:/posts/list";
    }

    @PostMapping("/likes/{postId}")
    @ResponseBody
    public Map<String, Object> toggleLike(@PathVariable("postId") Long postId,
                                         @SessionAttribute(name = "loginUser") User loginUser) {
        Post post = postService.findById(postId);
        User user = userService.findById(loginUser.getId());
        likeService.likeStatus(user, post);
        int updatedLikesCount = post.getLikesCount();

        Map<String, Object> response = new HashMap<>();
        response.put("likesCount", updatedLikesCount);
        return response;
    }
}
