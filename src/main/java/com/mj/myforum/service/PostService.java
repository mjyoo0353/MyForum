package com.mj.myforum.service;

import com.mj.myforum.domain.Post;
import com.mj.myforum.domain.User;
import com.mj.myforum.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public Post save(String title, String content, User user) {
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        post.setUser(user);
        post.setCreatedDate(LocalDateTime.now());
        post.setViews(0L);
        return postRepository.save(post);
    }

    public void update(Post post, String title, String content) {
        post.setTitle(title);
        post.setContent(content); //수정된 제목, 내용으로 업데이트
        post.setModifiedDate(LocalDateTime.now());
        postRepository.save(post);
    }

    public void delete(Post post) {
        postRepository.delete(post);
    }

    public Post findById(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new IllegalStateException("Post not found"));
    }

    public List<Post> postList() {
        return postRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    public Post getPost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalStateException("Post not found"));
        post.setViews(post.getViews() + 1); //조회수 증가
        postRepository.save(post); //post 조회수 증가 update
        return post;
    }


}
