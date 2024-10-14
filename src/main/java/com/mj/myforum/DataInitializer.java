package com.mj.myforum;

import com.mj.myforum.domain.Post;
import com.mj.myforum.domain.User;
import com.mj.myforum.repository.PostRepository;
import com.mj.myforum.repository.UserRepository;
import com.mj.myforum.service.PostService;
import com.mj.myforum.service.UserService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DataInitializer {

    private final PostService postService;
    private final UserService userService;

    @PostConstruct
    public void init(){
        User user = userService.save("testUser", "test", "테스트유저", "test@test");
        for (int i = 1; i <= 265; i++) {
            postService.save("게시글 테스트" + i, "내용입니다.", user);
        }
    }
}
