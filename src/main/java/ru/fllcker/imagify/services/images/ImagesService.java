package ru.fllcker.imagify.services.images;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.fllcker.imagify.models.Image;
import ru.fllcker.imagify.models.Post;
import ru.fllcker.imagify.repositories.IImagesRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class ImagesService {
    private final IImagesRepository imagesRepository;

    public Image create(String source, Post post) {
        Image image = new Image(source);
        image.setPost(post);
        imagesRepository.save(image);
        return image;
    }
}
