package ru.fllcker.imagify.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.fllcker.imagify.dto.CreatePostDto;
import ru.fllcker.imagify.dto.UpdatePostDto;
import ru.fllcker.imagify.models.Post;
import ru.fllcker.imagify.services.auth.AuthService;
import ru.fllcker.imagify.services.posts.PostsService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/posts")
public class PostsController {
    private final PostsService postsService;
    private final AuthService authService;

    @PostMapping("create")
    public ResponseEntity<Post> create(@RequestBody @Valid CreatePostDto createPostDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, bindingResult.getAllErrors().get(0).getDefaultMessage());

        String ae = authService.getAuthInfo().getEmail();
        Post post = postsService.create(ae, createPostDto);
        return ResponseEntity.ok(post);
    }

    @GetMapping("user/posts")
    public ResponseEntity<List<Post>> getUserPosts() {
        String ae = authService.getAuthInfo().getEmail();
        List<Post> posts = postsService.getUsersPosts(ae);
        return ResponseEntity.ok(posts);
    }

    @PostMapping("update")
    public ResponseEntity<Post> update(@RequestBody @Valid UpdatePostDto updatePostDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, bindingResult.getAllErrors().get(0).getDefaultMessage());

        String ae = authService.getAuthInfo().getEmail();
        Post post = postsService.update(ae, updatePostDto.getPostId(), updatePostDto);
        return ResponseEntity.ok(post);
    }
}
