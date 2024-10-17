package com.mj.myforum.controller;

import com.mj.myforum.domain.Comment;
import com.mj.myforum.domain.Post;
import com.mj.myforum.domain.User;
import com.mj.myforum.form.CommentForm;
import com.mj.myforum.form.PostForm;
import com.mj.myforum.service.CommentService;
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

import java.util.List;
import java.util.Optional;

@RequestMapping("/posts")
@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final UserService userService;
    private final CommentService commentService;


    @GetMapping("/list")
    public String postList(@RequestParam(value = "page", defaultValue = "0") int page, Model model) {

        Page<Post> postList = postService.getPostList(page);
        model.addAttribute("postList", postList);
        return "posts/postList";
    }

    @GetMapping("/search")
    public String postList(@RequestParam(value = "keyword", required = false) String keyword,
                                @RequestParam(value = "page", defaultValue = "0") int page,
                                Model model) {

        Page<Post> postList = postService.searchPosts(keyword, page);
        model.addAttribute("postList", postList);
        return "posts/postList";
    }

    //작성한 글 보기
    @GetMapping("/{postId}")
    public String getPost(@PathVariable Long postId, Model model, CommentForm commentForm,
                          @SessionAttribute(name = "loginUser", required = false) User loginUser) {

        Post post = postService.getPost(postId);
        List<Comment> comments = commentService.commentList(postId);
        model.addAttribute("post", post);
        model.addAttribute("loginUser", loginUser);
        model.addAttribute("commentForm", commentForm);
        model.addAttribute("comments", comments);
        return "posts/getPost";
    }

    //게시글 작성 폼 불러오기
    @GetMapping("/create")
    public String createPostForm(@ModelAttribute("form") PostForm form) {
        return "posts/createPost";
    }

    //게시글 작성
    @PostMapping("/create")
    public String createPost(@Validated @ModelAttribute("form") PostForm form, BindingResult bindingResult,
                             @SessionAttribute(name = "loginUser", required = false) User loginUser,
                             RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "posts/createPost";
        }
        Optional<User> user = userService.findByLoginId(loginUser.getLoginId());
        Post savedPost = postService.save(form.getTitle(), form.getContent(), user.get());
        redirectAttributes.addAttribute("postId", savedPost.getId());
        return "redirect:/posts/{postId}";
    }

    //게시글 수정하기
    @GetMapping("/edit")
    public String editPostForm(PostForm form, @RequestParam("postId") Long postId, Model model) {
        Post post = postService.findById(postId); //수정할 post 객체 불러오기
        form.setTitle(post.getTitle());
        form.setContent(post.getContent()); //뷰에 보일 postForm에 수정 전 데이터 저장

        model.addAttribute("form", form); //해당 form 뷰에 전달
        model.addAttribute("postId", postId); //수정할 id도 전달*/
        return "posts/editPost";
    }

    @PostMapping("/edit/{postId}")
    public String editPost(@Validated @ModelAttribute("form") PostForm form, BindingResult bindingResult,
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

}
