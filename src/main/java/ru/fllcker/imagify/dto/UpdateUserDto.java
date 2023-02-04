package ru.fllcker.imagify.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UpdateUserDto {
    @Size(min = 3, max = 16, message = "Name should be more than 3 letters and less than 16 letters!")
    private String firstname;
    @Size(min = 2, max = 32, message = "Description should be more than 2 letters and less than 32 letters!")
    private String description;
}
