package ru.fllcker.imagify.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fllcker.imagify.models.Subscription;

import java.util.List;

@Repository
public interface ISubscriptionsRepository extends JpaRepository<Subscription, Integer> {
    boolean existsByOwnerEmailAndTargetEmail(String owner_email, String target_email);
    List<Subscription> findSubscriptionsByOwnerEmail(String owner_email);
    List<Subscription> findSubscriptionsByTargetEmail(String target_email);
}
