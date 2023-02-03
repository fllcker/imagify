package ru.fllcker.imagify.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fllcker.imagify.models.Comment;

@Repository
public interface ICommentsRepository extends JpaRepository<Comment, Integer> {
}
