package ru.fllcker.imagify.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fllcker.imagify.models.Subscription;

@Repository
public interface ISubscriptionsRepository extends JpaRepository<Subscription, Integer> {
}
