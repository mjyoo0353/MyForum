package com.mj.myforum.service;

import com.mj.myforum.domain.User;
import com.mj.myforum.form.SignupForm;
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
        SignupForm user = new SignupForm();
        user.setLoginId("test");
        user.setPassword("test");
        user.setName("testUser");
        user.setEmail("test@gmail.com");

        //When
        User savedUser = userService.save(user);

        //Then
        User findUser = userRepository.findById(savedUser.getId());
        assertEquals(user.getLoginId(), findUser.getLoginId());
    }

    @Test
    public void duplicateUserCheck() throws Exception {
        //Given
        SignupForm user1 = new SignupForm();
        user1.setLoginId("test");
        user1.setPassword("test");
        user1.setName("testUser");
        user1.setEmail("test@gmail.com");

        SignupForm user2 = new SignupForm();
        user2.setLoginId("test");
        user2.setPassword("test");
        user2.setName("testUser");
        user2.setEmail("test@gmail.com");

        //When
        User savedUser1 = userService.save(user1);
        User savedUser2 = userService.save(user2);

        //Then
        User findUser1 = userRepository.findById(savedUser1.getId());
        User findUser2 = userRepository.findById(savedUser2.getId());
        assertEquals(findUser1.getLoginId(), findUser2.getLoginId());
    }

}