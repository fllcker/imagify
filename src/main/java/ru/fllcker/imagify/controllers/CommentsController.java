package ru.fllcker.imagify.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.fllcker.imagify.dto.CreateCommentDto;
import ru.fllcker.imagify.models.Comment;
import ru.fllcker.imagify.services.auth.AuthService;
import ru.fllcker.imagify.services.comments.CommentsService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/comments")
public class CommentsController {
    private final CommentsService commentsService;
    private final AuthService authService;

    @PostMapping("create/{postId}")
    public ResponseEntity<Comment> create(@RequestBody @Valid CreateCommentDto createCommentDto, BindingResult bindingResult, @PathVariable Integer postId) {
        if (bindingResult.hasErrors())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, bindingResult.getAllErrors().get(0).getDefaultMessage());

        String ae = authService.getAuthInfo().getEmail();
        Comment comment = commentsService.create(ae, postId, createCommentDto);
        return ResponseEntity.ok(comment);
    }

    @GetMapping("post/{postId}")
    public ResponseEntity<List<Comment>> getByPost(@PathVariable Integer postId) {
        return ResponseEntity.ok(commentsService.getPostComments(postId));
    }
}
