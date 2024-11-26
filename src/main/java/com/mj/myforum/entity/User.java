package com.mj.myforum.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String loginId;

    @Column(nullable = false, length = 20)
    private String password;

    @Column(nullable = false, length = 20)
    private String username;

    @Email @Column(nullable = false, length = 40)
    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Likes> likes = new ArrayList<>();

    public static User createUser(String loginId, String password, String name, String email) {
        User user = new User();
        user.setLoginId(loginId);
        user.setPassword(password);
        user.setUsername(name);
        user.setEmail(email);
        return user;
    }

    public int getPostCount(){
        return posts.size();
    }

    public int getCommentCount(){
        return comments.size();
    }

    public int getLikesCount(){
        return likes.size();
    }

    public User(String loginId, String password, String username, String email) {
        this.loginId = loginId;
        this.password = password;
        this.username = username;
        this.email = email;
    }

}
