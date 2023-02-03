package ru.fllcker.imagify.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fllcker.imagify.models.Like;

@Repository
public interface ILikesRepository extends JpaRepository<Like, Integer> {
}
