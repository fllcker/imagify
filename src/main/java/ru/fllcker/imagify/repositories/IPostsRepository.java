package ru.fllcker.imagify.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.fllcker.imagify.models.Post;
import ru.fllcker.imagify.models.User;

import java.util.List;

@Repository
public interface IPostsRepository extends JpaRepository<Post, Integer> {
    @Transactional
    @Modifying
    @Query("update posts p set p.description = ?1 where p.id = ?2")
    void updateDescriptionById(String description, int id);
    List<Post> findPostsByOwner(User owner);

    
}
