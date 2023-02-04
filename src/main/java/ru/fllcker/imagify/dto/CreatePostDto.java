package ru.fllcker.imagify.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CreatePostDto {
    @NonNull
    @Size(min = 3, max = 64, message = "Description should be more than 3 letters and less than 64 letters!")
    private String description;
}
