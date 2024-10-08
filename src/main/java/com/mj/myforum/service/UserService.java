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
    public Long save(User user) {
        validateDuplicateUserLoginId(user); //중복 회원 검증
        userRepository.save(user);
        return user.getId();
    }

    //중복 회원 검사
    private void validateDuplicateUserLoginId(User user) {
        Optional<User> existingUser = userRepository.findByLoginId(user.getLoginId());
        if (existingUser.isPresent()) {
            throw new IllegalStateException("이미 존재하는 아이디입니다.");
        }
    }

    //전체 회원 조회
    public List<User> findAll(){
        return userRepository.findAll();
    }

    //id로 조회
    public User findById(Long userId) {
        return userRepository.findById(userId);
    }

    public Optional<User> findByLoginId(String loginId) {
        return userRepository.findByLoginId(loginId);
    }
}
