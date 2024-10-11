package com.mj.myforum.repository;

import com.mj.myforum.domain.Post;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostRepository{

    @PersistenceContext
    private final EntityManager em;

    public Post save(Post post) {
        em.persist(post);
        return post;
    }

    public void delete(Post post) {
        em.remove(post);
    }

    public Post findById(Long postId) {
        return em.createQuery("select p from Post p where p.id = :post_id", Post.class)
                .setParameter("post_id", postId)
                .getSingleResult();
    }

    public List<Post> findAll(){
        return em.createQuery("select p from Post p", Post.class)
                .getResultList();
    }

    // limit 이용해서 한 페이지당 게시글 10개만 출력하기 TODO
    public List<Post> postsNumPerPage() {
        return em.createQuery("select p from Post p order by p.id desc", Post.class)
                .getResultList();
    }

}
