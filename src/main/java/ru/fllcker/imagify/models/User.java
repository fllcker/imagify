package ru.fllcker.imagify.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Entity(name = "users")
@NoArgsConstructor
@Getter @Setter
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "created_at")
    private Instant createdAt = Instant.now();

    private String email;
    private String username;
    private String password;
    private String firstname;
    private String description;


    @OneToMany(mappedBy = "owner")
    private List<Post> posts;

    @OneToMany(mappedBy = "owner")
    private List<Comment> comments;

    @OneToMany(mappedBy = "owner")
    private List<Like> likes;

    @OneToMany(mappedBy = "owner")
    private List<Subscription> subscriptions;

    @OneToMany(mappedBy = "target")
    private List<Subscription> subscribers;
}
