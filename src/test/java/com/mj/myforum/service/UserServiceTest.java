package com.mj.myforum.service;

import com.mj.myforum.domain.User;
import com.mj.myforum.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserServiceTest {

    @Autowired UserService userService;
    @Autowired UserRepository userRepository;

    @Test
    public void signup() throws Exception {
        //Given
        User user = new User("testId", "testPw", "spring");

        //When
        Long saveId = userService.save(user);

        //Then
        User findUser = userRepository.findById(saveId);
        assertEquals(user.getLoginId(), findUser.getLoginId());
    }

    @Test
    public void duplicateUserCheck() throws Exception {
        //Given
        User user1 = new User("java", "1234", "test");
        User user2 = new User("java", "1234", "test");

        //When
        userService.save(user1);

        //Then
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> userService.save(user2));
        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 아이디입니다.");
    }

}