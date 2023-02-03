package ru.fllcker.imagify.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fllcker.imagify.models.User;

@Repository
public interface IUsersRepository extends JpaRepository<User, Integer> {
}
