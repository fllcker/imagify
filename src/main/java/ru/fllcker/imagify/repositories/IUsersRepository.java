package ru.fllcker.imagify.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.fllcker.imagify.models.User;

import java.util.Optional;

@Repository
public interface IUsersRepository extends JpaRepository<User, Integer> {
    @Transactional
    @Modifying
    @Query("update users u set u.firstname = ?1, u.description = ?2 where u.email = ?3")
    void updateFirstnameAndDescriptionByEmail(String firstname, String description, String email);
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);

}
