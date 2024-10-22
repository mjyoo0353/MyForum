package com.mj.myforum.service;

import com.mj.myforum.domain.User;
import com.mj.myforum.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor //생성자 인젝션 자동 생성
public class UserService {

    //@RequiredArgsConstructor를 통해 생성자를 생성했기 때문에 변경될 일이 없어 final로 만들어줌
    private final UserRepository userRepository;

    //회원가입
    @Transactional //기본 default는 false, 회원가입은 읽고 쓰도록 설정
    public User save(String loginId, String password, String name, String email) {
        User user = User.createUser(loginId, password, name, email);
        userRepository.save(user);
        return user;
    }

    //중복 회원 검사
    public boolean isLoginIdDuplicated(String loginId) {
        Optional<User> existingUser = userRepository.findByLoginId(loginId);
        if (existingUser.isPresent()) {
            return true;
        }
        return false;
    }

    public boolean isPasswordSame(String password, String passwordCheck) {
        if (password.equals(passwordCheck)) {
            return true;
        }
        return false;
    }

    //전체 회원 조회
    public List<User> findAll(){
        return userRepository.findAll();
    }

    //id로 조회
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("User not found"));
    }

    public Optional<User> findByLoginId(String loginId) {
        return userRepository.findByLoginId(loginId);
    }
}
