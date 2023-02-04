package ru.fllcker.imagify.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import ru.fllcker.imagify.dto.UpdateUserDto;
import ru.fllcker.imagify.services.auth.AuthService;
import ru.fllcker.imagify.services.users.UsersService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/users")
public class UsersController {
    private final AuthService authService;
    private final UsersService usersService;

    @PostMapping("update/profile")
    public ResponseEntity<String> updateProfile(@RequestBody @Valid UpdateUserDto updateUserDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, bindingResult.getAllErrors().get(0).getDefaultMessage());

        String ae = authService.getAuthInfo().getEmail();
        usersService.update(ae, updateUserDto);
        return ResponseEntity.ok().build();
    }
}
