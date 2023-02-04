package ru.fllcker.imagify.services.posts;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import ru.fllcker.imagify.dto.CreatePostDto;
import ru.fllcker.imagify.dto.UpdatePostDto;
import ru.fllcker.imagify.models.Post;
import ru.fllcker.imagify.models.User;
import ru.fllcker.imagify.repositories.IPostsRepository;
import ru.fllcker.imagify.services.users.UsersService;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostsService {
    private final IPostsRepository postsRepository;
    private final UsersService usersService;

    public Optional<Post> findById(int id) {
        return postsRepository.findById(id);
    }

    public Post create(String ae, CreatePostDto createPostDto) {
        Post post = new Post(createPostDto.getDescription());

        User owner = usersService.findByEmail(ae)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!"));
        post.setOwner(owner);

        postsRepository.save(post);
        return post;
    }

    public List<Post> getUsersPosts(String ae) {
        User user = usersService.findByEmail(ae)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!"));

        return postsRepository.findPostsByOwner(user);
    }

    public Post update(String ae, Integer postId, UpdatePostDto updatePostDto) {
        User user = usersService.findByEmail(ae)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!"));
        Post post = postsRepository.findById(postId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found!"));

        if (post.getOwner().getId() != user.getId())
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "No access to this post!");

        postsRepository.updateDescriptionById(updatePostDto.getDescription(), post.getId());
        post.setDescription(updatePostDto.getDescription());
        return post;
    }
}
