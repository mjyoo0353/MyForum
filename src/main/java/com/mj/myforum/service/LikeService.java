package com.mj.myforum.service;

import com.mj.myforum.domain.Likes;
import com.mj.myforum.domain.Post;
import com.mj.myforum.domain.User;
import com.mj.myforum.repository.LikeRepository;
import com.mj.myforum.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;

    public void likeStatus(User user, Post post) {
        if(likeRepository.existsByUserAndPost(user, post)) {
            //좋아요를 누른 상태면 삭제하기
            Likes existingLike  = likeRepository.findByUserAndPost(user, post);
            likeRepository.delete(existingLike);
            return;
        }
        Likes addLike = Likes.createLike(user, post);
        likeRepository.save(addLike);
    }

}