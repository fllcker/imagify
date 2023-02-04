package ru.fllcker.imagify.controllers;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import ru.fllcker.imagify.models.Image;
import ru.fllcker.imagify.services.auth.AuthService;
import ru.fllcker.imagify.services.images.ImagesService;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/images")
public class ImagesController {
    private final HttpServletRequest request;
    private final ImagesService imagesService;
    private final AuthService authService;

    @PostMapping("upload/{postId}")
    public ResponseEntity<Image> handleFileUpload(@RequestParam("file") MultipartFile file, @PathVariable Integer postId) throws IOException {
        if (!Objects.equals(file.getContentType(), "image/jpeg") &&
            !Objects.equals(file.getContentType(), "image/png"))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Image type should be png/jpeg!");

        String filePath = request.getServletContext().getRealPath("/uploads/");
        if(! new File(filePath).exists()) {
            new File(filePath).mkdir();
        }

        String originalFileName = Objects.requireNonNull(file.getOriginalFilename())
                .replace("/", "")
                .replace("\\", "")
                .replace("\\", "")
                .replace(":", "")
                .replace("?", "")
                .replace("*", "")
                .replace("\"", "")
                .replace("|", "")
                .replace("<", "")
                .replace(">", "");

        String fileName = new Random().nextInt(1000000) + originalFileName;
        String resultFilePath = filePath + "/" + fileName;
        file.transferTo(new File(resultFilePath));

        String ae = authService.getAuthInfo().getEmail();
        Image image = imagesService.create(resultFilePath, postId, ae);
        return ResponseEntity.ok(image);
    }
}
