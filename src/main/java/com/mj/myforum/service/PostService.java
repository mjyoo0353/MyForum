package com.mj.myforum.service;

import com.mj.myforum.dto.PostResponseDto;
import com.mj.myforum.entity.Post;
import com.mj.myforum.entity.User;
import com.mj.myforum.repository.LikeRepository;
import com.mj.myforum.repository.PostRepository;
import com.mj.myforum.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final LikeRepository likeRepository;

    public Long save(Long userId, String title, String content) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalStateException("User not found"));
        Post post = Post.createPost(user, title, content);
        postRepository.save(post);
        return post.getId();
    }

    public void update(Post post, String title, String content) {
        post.setTitle(title);
        post.setContent(content); //수정된 제목, 내용으로 업데이트
        //post.setModifiedDate(LocalDateTime.now());
        postRepository.save(post);
    }

    public void delete(Post post) {
        postRepository.delete(post);
    }

    public Post findById(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new IllegalStateException("Post not found"));
    }

    public Page<Post> getPosts(String keyword, int page) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by("id").descending());

        if(keyword == null || keyword.trim().isEmpty()) {
            return postRepository.findAll(pageable);
        }else {
            return postRepository.findByKeyword(keyword, pageable);
        }
    }

    public PostResponseDto getPostById(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalStateException("Post not found"));
        return new PostResponseDto(post);
    }
}
