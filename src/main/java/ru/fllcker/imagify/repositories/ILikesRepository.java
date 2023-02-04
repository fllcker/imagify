package ru.fllcker.imagify.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fllcker.imagify.models.Like;
import ru.fllcker.imagify.models.Post;
import ru.fllcker.imagify.models.User;

@Repository
public interface ILikesRepository extends JpaRepository<Like, Integer> {
    boolean existsByOwnerAndPost(User owner, Post post);
}
