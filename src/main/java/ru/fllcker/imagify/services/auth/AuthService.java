package ru.fllcker.imagify.services.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import ru.fllcker.imagify.models.User;
import ru.fllcker.imagify.security.JwtAuthentication;
import ru.fllcker.imagify.security.JwtRequest;
import ru.fllcker.imagify.security.JwtResponse;
import ru.fllcker.imagify.security.SignUpDto;
import ru.fllcker.imagify.services.users.UsersService;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {
    private final UsersService usersService;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder encoder;
    private final ObjectMapper objectMapper;

    public JwtResponse login(JwtRequest jwtRequest) {
        User user = usersService.findByEmail(jwtRequest.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        if (!encoder.matches(jwtRequest.getPassword(), user.getPassword()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong data");

        String accessToken = jwtProvider.generateAccessToken(user);
        return new JwtResponse(accessToken);
    }

    public JwtResponse signup(SignUpDto dto) {
        if (usersService.existsByEmail(dto.getEmail()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User with this email already exists!");

        User user = objectMapper.convertValue(dto, User.class);
        user.setPassword(encoder.encode(user.getPassword()));
        usersService.create(user);

        String accessToken = jwtProvider.generateAccessToken(user);
        return new JwtResponse(accessToken);
    }

    public JwtAuthentication getAuthInfo() {
        return (JwtAuthentication) SecurityContextHolder.getContext().getAuthentication();
    }
}
