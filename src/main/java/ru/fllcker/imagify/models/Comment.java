package ru.fllcker.imagify.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity(name = "comments")
@NoArgsConstructor
@RequiredArgsConstructor
@Getter @Setter
public class Comment {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "created_at")
    private Instant createdAt = Instant.now();

    @NonNull
    private String value;

    @ManyToOne
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private User owner;
}
