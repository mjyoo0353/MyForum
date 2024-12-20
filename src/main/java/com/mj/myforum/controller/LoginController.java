package com.mj.myforum.controller;

import com.mj.myforum.dto.LoginForm;
import com.mj.myforum.entity.User;
import com.mj.myforum.service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/login")
    public String loginForm(@ModelAttribute("loginForm") LoginForm form) {
        return "login/loginForm";
    }

    @PostMapping("/login")
    public String login(@Validated @ModelAttribute("loginForm") LoginForm form, BindingResult bindingResult,
                        @RequestParam(defaultValue = "/")String redirectURL,
                        HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            return "login/loginForm";
        }

        //로그인 검증
        User loginUser = loginService.login(form.getLoginId(), form.getPassword());
        if (loginUser == null) {
            bindingResult.reject("loginFail", "Invalid login ID or password.");
            return "login/loginForm";
        }

        //로그인 성공 처리 (세션이 있으면 세션 반환, 없으면 신규 세션 생성)
        HttpSession session = request.getSession();
        //세션에 로그인 회원 정보 보관
        session.setAttribute("loginUser", loginUser);

        if (redirectURL != null) {
            return "redirect:" + redirectURL;
        }
        return "redirect:/posts/list";
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/posts/list";
    }
}
