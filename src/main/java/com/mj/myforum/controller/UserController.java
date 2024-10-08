package com.mj.myforum.controller;

import com.mj.myforum.domain.User;
import com.mj.myforum.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/signup")
    public String signupForm(@ModelAttribute("user") User user){
        return "users/signupForm";
    }

    @PostMapping("/signup")
    public String signup(@Validated @ModelAttribute("user") User user, BindingResult bindingResult){

        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> log.error(error.getDefaultMessage()));
            return "users/signupForm";
        }

        Optional<User> existingUser = userService.findByLoginId(user.getLoginId());

        //중복아이디 검증
        if (existingUser.isPresent()) {
            bindingResult.rejectValue("loginId","아이디 중복", "이미 존재하는 아이디입니다.");
            log.error("check duplicated user failed: {}", user.getLoginId());
            return "users/signupForm";
        }

        user.setLoginId(user.getLoginId());
        user.setPassword(user.getPassword());
        user.setName(user.getName());
        userService.save(user);
        return "redirect:/";
    }

    @GetMapping("/userList")
    public String list(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "users/userList";
    }
}
