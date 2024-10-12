package com.mj.myforum.controller;

import com.mj.myforum.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(){
        return "redirect:/posts/list";
    }

    //@GetMapping("/")
    public String homeLogin(@SessionAttribute(name = "loginUser", required = false)User loginUser,
                            Model model){
        //세션에 회원 데이터가 없으면 home
        if (loginUser == null) {
            return "home";
        }

        //세션이 유지되면 로그인된 홈으로 이동
        model.addAttribute("user", loginUser);
        return "redirect:/posts/list";
    }


}
