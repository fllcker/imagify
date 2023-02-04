package ru.fllcker.imagify.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.fllcker.imagify.models.Like;
import ru.fllcker.imagify.services.auth.AuthService;
import ru.fllcker.imagify.services.likes.LikesService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/likes")
public class LikesController {
    private final LikesService likesService;
    private final AuthService authService;

    @PostMapping("/{postId}")
    public ResponseEntity<Like> create(@PathVariable Integer postId) {
        String ae = authService.getAuthInfo().getEmail();
        return ResponseEntity.ok(likesService.create(ae, postId));
    }

    @DeleteMapping("/{likeId}")
    public ResponseEntity<String> delete(@PathVariable Integer likeId) {
        String ae = authService.getAuthInfo().getEmail();
        likesService.delete(ae, likeId);
        return ResponseEntity.ok().build();
    }
}
