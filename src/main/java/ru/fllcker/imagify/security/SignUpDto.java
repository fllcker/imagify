package ru.fllcker.imagify.security;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpDto {
    @Email(message = "Email is not valid!")
    private String email;

    @Size(min = 4, max = 16, message = "Username should be more than 4 letters and less than 16 letters!")
    private String username;

    @Size(min = 8, message = "Password should be more than 8 letters!")
    private String password;

    @Size(min = 3, max = 16, message = "Name should be more than 3 letters and less than 16 letters!")
    private String firstname;
}
