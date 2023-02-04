package ru.fllcker.imagify.dto;

import lombok.Getter;
import lombok.Setter;

public class UpdatePostDto extends CreatePostDto {
    @Getter @Setter
    private int postId;
}
