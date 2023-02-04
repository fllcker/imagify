package ru.fllcker.imagify.services.images;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import ru.fllcker.imagify.models.Image;
import ru.fllcker.imagify.models.Post;
import ru.fllcker.imagify.repositories.IImagesRepository;
import ru.fllcker.imagify.services.posts.PostsService;

@Service
@Transactional
@RequiredArgsConstructor
public class ImagesService {
    private final IImagesRepository imagesRepository;
    private final PostsService postsService;

    public Image create(String source, int postId, String ae) {
        Post post = postsService.findById(postId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found!"));

        if (!post.getOwner().getEmail().equals(ae))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "No access to this post!");

        Image image = new Image(source);
        image.setPost(post);
        imagesRepository.save(image);
        return image;
    }
}
