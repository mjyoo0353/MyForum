package com.mj.myforum.repository;

import com.mj.myforum.domain.Likes;
import com.mj.myforum.domain.Post;
import com.mj.myforum.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Likes, Long> {

    //특정 사용자가 특정 게시글에 좋아요를 눌렀는지 확인
    boolean existsByUserAndPost(User user, Post post);

    Likes findByUserAndPost(User user, Post post);

}
