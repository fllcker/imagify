package ru.fllcker.imagify.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fllcker.imagify.models.Image;

@Repository
public interface IImagesRepository extends JpaRepository<Image, Integer> {
}
