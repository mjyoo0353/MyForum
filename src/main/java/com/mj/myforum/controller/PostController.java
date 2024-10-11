package com.mj.myforum.controller;

import com.mj.myforum.domain.Post;
import com.mj.myforum.domain.User;
import com.mj.myforum.form.PostForm;
import com.mj.myforum.service.PostService;
import com.mj.myforum.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@RequestMapping("/posts")
@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final UserService userService;

    @GetMapping("/list")
    public String postList(@SessionAttribute(name = "loginUser", required = false) User loginUser,
                           Model model) {

        //로그인 여부 체크
        model.addAttribute("show", checkLogin(loginUser, model));
        //게시물 목록 가져오기
        List<Post> postList = postService.postList();
        model.addAttribute("postList", postList);
        return "posts/postList";
    }

    /**
     * 로그인 체크 메서드
     */
    private boolean checkLogin(User loginUser, Model model) {
        if (loginUser == null) {
            return false;
        } else {
            model.addAttribute("user", userService.findById(loginUser.getId()));
            return true;
        }
    }

    //작성한 글 보기
    @GetMapping("/{postId}")
    public String getPost(@PathVariable Long postId, Model model,
                          @SessionAttribute(name = "loginUser", required = false) User loginUser) {

        if (postService.isAccessable(postId, loginUser)) {
            model.addAttribute("access", true);
        }
        Post post = postService.getPost(postId);
        model.addAttribute("post", post);
        return "posts/getPost";
    }

    //게시글 작성 폼 불러오기
    @GetMapping("/create")
    public String createPostForm(@ModelAttribute("postForm") PostForm postForm) {
        return "posts/createPost";
    }

    //게시글 작성
    @PostMapping("/create")
    public String createPost(@Validated @ModelAttribute("postForm") PostForm postForm, BindingResult bindingResult,
                             @SessionAttribute(name = "loginUser", required = false) User loginUser,
                             RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "posts/createPost";
        }

        //로그인 유저 정보 검증
        Post savedPost = postService.save(loginUser, postForm);

        redirectAttributes.addAttribute("postId", savedPost.getId());
        return "redirect:/posts/{postId}";
    }

    //게시글 수정하기
    @GetMapping("/edit")
    public String editPostForm(@RequestParam("postId") Long postId, Model model) {
        Post post = postService.findById(postId); //수정할 post 객체 불러오기
        PostForm postForm = new PostForm();
        postForm.setTitle(post.getTitle());
        postForm.setContent(post.getContent()); //뷰에 보일 postForm에 수정 전 데이터 저장

        model.addAttribute("postForm", postForm); //해당 form 뷰에 전달
        model.addAttribute("postId", postId); //수정할 id도 전달*/

        return "posts/editPost";
    }

    @PostMapping("/edit/{postId}")
    public String editPost(@PathVariable Long postId,
                           @Validated @ModelAttribute("postForm") PostForm postForm, BindingResult bindingResult,
                           Model model, RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()) {
            model.addAttribute("postId", postId);
            return "posts/editPost";
        }
        postService.update(postId, postForm);
        redirectAttributes.addAttribute("postId", postId);
        return "redirect:/posts/{postId}";
    }

    //게시글 삭제하기
    @GetMapping("/delete")
    public String deletePost(@RequestParam("postId") Long postId) {
        postService.delete(postId);
        return "redirect:/posts/list";
    }

}
