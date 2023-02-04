package ru.fllcker.imagify.services.likes;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import ru.fllcker.imagify.models.Like;
import ru.fllcker.imagify.models.Post;
import ru.fllcker.imagify.models.User;
import ru.fllcker.imagify.repositories.ILikesRepository;
import ru.fllcker.imagify.services.posts.PostsService;
import ru.fllcker.imagify.services.users.UsersService;

@Service
@Transactional
@RequiredArgsConstructor
public class LikesService {
    private final ILikesRepository likesRepository;
    private final UsersService usersService;
    private final PostsService postsService;

    public Like create(String ae, Integer postId) {
        User user = usersService.findByEmail(ae)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!"));

        Post post = postsService.findById(postId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found!"));

        if (likesRepository.existsByOwnerAndPost(user, post))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Your like on this post already exists");

        Like like = new Like();
        like.setOwner(user);
        like.setPost(post);
        likesRepository.save(like);
        return like;
    }

    public void delete(String ae, Integer likeId) {
        Like like = likesRepository.findById(likeId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Like data not found!"));

        if (!like.getOwner().getEmail().equals(ae))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "No access to  this like data");

        likesRepository.delete(like);
    }
}
