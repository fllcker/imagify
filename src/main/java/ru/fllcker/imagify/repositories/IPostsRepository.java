package ru.fllcker.imagify.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fllcker.imagify.models.Post;

@Repository
public interface IPostsRepository extends JpaRepository<Post, Integer> {
}
