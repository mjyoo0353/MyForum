package com.mj.myforum.service;

import com.mj.myforum.entity.Comment;
import com.mj.myforum.entity.Post;
import com.mj.myforum.entity.User;
import com.mj.myforum.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public List<Comment> commentList(Long postId){
        return commentRepository.findByPostId(postId);
    }

    public Comment findById(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Comment not found"));
    }

    public Comment save(Post post, User user, String content) {
        Comment comment = Comment.createComment(post, user, content);
        commentRepository.save(comment);
        return comment;
    }

    public void update(Comment comment, String content) {
        comment.setContent(content);
        comment.setModifiedDate(LocalDateTime.now());
        commentRepository.save(comment);
    }

    public void delete(Comment comment) {
        commentRepository.delete(comment);
    }

    public Comment getComment(Long commentId) {
        return commentRepository.findById(commentId).orElseThrow(() -> new IllegalStateException("Comment not found"));
    }
}
