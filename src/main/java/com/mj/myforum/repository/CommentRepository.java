package com.mj.myforum.repository;

import com.mj.myforum.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("select c from Comment c join fetch c.user where c.post.id = :postId")
    List<Comment> findByPostId(Long postId);
}
