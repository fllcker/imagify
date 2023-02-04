package ru.fllcker.imagify.services.comments;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import ru.fllcker.imagify.dto.CreateCommentDto;
import ru.fllcker.imagify.models.Comment;
import ru.fllcker.imagify.models.Post;
import ru.fllcker.imagify.models.User;
import ru.fllcker.imagify.repositories.ICommentsRepository;
import ru.fllcker.imagify.services.posts.PostsService;
import ru.fllcker.imagify.services.users.UsersService;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentsService {
    private final ICommentsRepository commentsRepository;
    private final PostsService postsService;
    private final UsersService usersService;

    public Comment create(String ae, Integer postId, CreateCommentDto createCommentDto) {
        Post post = postsService.findById(postId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found!"));

        User owner = usersService.findByEmail(ae)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!"));

        Comment comment = new Comment(createCommentDto.getValue());
        comment.setOwner(owner);
        comment.setPost(post);
        commentsRepository.save(comment);
        return comment;
    }

    public List<Comment> getPostComments(Integer postId) {
        return commentsRepository.findCommentsByPostId(postId);
    }
}
