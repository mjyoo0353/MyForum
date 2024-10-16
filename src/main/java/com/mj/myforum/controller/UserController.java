package com.mj.myforum.controller;

import com.mj.myforum.domain.Post;
import com.mj.myforum.domain.User;
import com.mj.myforum.form.SignupForm;
import com.mj.myforum.service.PostService;
import com.mj.myforum.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/signup")
    public String signupForm(@ModelAttribute("form") SignupForm form){
        return "users/signupForm";
    }

    @PostMapping("/signup")
    public String signup(@Validated @ModelAttribute("form") SignupForm form, BindingResult bindingResult){

        //LoginId 중복 체크
        if (userService.isLoginIdDuplicated(form.getLoginId())) {
            bindingResult.rejectValue("loginId","loginIdDuplicated", "이미 존재하는 아이디입니다.");
        }

        //비밀번호 일치 확인
        if (!userService.isPasswordSame(form.getPassword(), form.getPasswordCheck())) {
            bindingResult.rejectValue("passwordCheck", "passwordCheckError", "비밀번호가 일치하지 않습니다. 다시 확인해주세요.");
        }

        if (bindingResult.hasErrors()) {
            return "users/signupForm";
        }

        User savedUser = userService.save(form.getLoginId(), form.getPassword(), form.getName(), form.getEmail());
        return "redirect:/";
    }

    @GetMapping("/userList")
    public String list(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "users/userList";
    }

    @GetMapping("/profile")
    public String profile(@SessionAttribute(name = "loginUser", required = false) User loginUser,
                          Model model) {
        if (loginUser != null) {
            User userId = userService.findById(loginUser.getId());
            model.addAttribute("user", userId);
            model.addAttribute("posts", userId.getPosts());
            model.addAttribute("comments", userId.getComments());
        }
        return "users/getProfile";
    }
}
