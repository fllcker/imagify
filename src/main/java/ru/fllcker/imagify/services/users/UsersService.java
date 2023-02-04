package ru.fllcker.imagify.services.users;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import ru.fllcker.imagify.dto.UpdateUserDto;
import ru.fllcker.imagify.models.User;
import ru.fllcker.imagify.repositories.IUsersRepository;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UsersService {
    private final IUsersRepository usersRepository;

    public Optional<User> findByEmail(String email) {
        return usersRepository.findByEmail(email);
    }

    public boolean existsByEmail(String email) {
        return usersRepository.existsByEmail(email);
    }

    public void create(User user) {
        usersRepository.save(user);
    }

    public void update(String ae, UpdateUserDto dto) {
        if (!usersRepository.existsByEmail(ae))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!");

        usersRepository.updateFirstnameAndDescriptionByEmail(dto.getFirstname(), dto.getDescription(), ae);
    }
}
