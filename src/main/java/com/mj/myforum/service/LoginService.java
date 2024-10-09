package com.mj.myforum.service;

import com.mj.myforum.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserService userService;

    /**
     * @return 로그인 성공 시 User 객체, 실패 시 null
     */
    public User login(String loginId, String password) {
        return userService.findByLoginId(loginId)
                .filter(user -> user.getPassword().equals(password)) //비밀번호가 같으면 member 반환
                .orElse(null); // 그렇지 않으면 null 반환
    }
}
