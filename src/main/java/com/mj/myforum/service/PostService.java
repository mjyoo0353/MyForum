package com.mj.myforum.service;

import com.mj.myforum.domain.Post;
import com.mj.myforum.domain.User;
import com.mj.myforum.form.PostForm;
import com.mj.myforum.repository.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public Post save(User user, PostForm postForm) {
        Post post = new Post();
        post.setTitle(postForm.getTitle());
        post.setContent(postForm.getContent());
        post.setWriter(user.getLoginId());
        post.setCreatedDate(LocalDateTime.now());
        post.setViews(0L);
        return postRepository.save(post);
    }

    public void update(Long postId, PostForm postForm) {
        Post post = postRepository.findById(postId); //수정할 post 불러오기
        post.setTitle(postForm.getTitle());
        post.setContent(postForm.getContent()); //수정된 제목, 내용으로 업데이트
        postRepository.save(post);
    }

    public void delete(Long postId) {
        Post post = postRepository.findById(postId);
        postRepository.delete(post);
    }

    public Post findById(Long postId) {
        return postRepository.findById(postId);
    }

    public List<Post> postList() {
        return postRepository.findAll();
    }

    public Post getPost(Long postId) {
        Post post = postRepository.findById(postId);
        post.setViews(post.getViews() + 1); //조회수 증가
        postRepository.save(post); //post 조회수 증가 update
        return post;
    }

    public boolean isAccessable(Long postId, User loginUser) {
        Post post = postRepository.findById(postId);
        if (post.getWriter().equals(loginUser.getLoginId())) {
            return true;
        } else {
            return false;
        }
    }


}
