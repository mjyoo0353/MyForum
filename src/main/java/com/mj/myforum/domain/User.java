package com.mj.myforum.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false)
    private String loginId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Email @Column(nullable = false)
    private String email;

    public User() {
    }

    public User(String loginId, String password) {
        this.loginId = loginId;
        this.password = password;
    }

    public User(String loginId, String password, String name, String email) {
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.email = email;
    }
}
