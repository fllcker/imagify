package ru.fllcker.imagify.services.subscriptions;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import ru.fllcker.imagify.models.Subscription;
import ru.fllcker.imagify.models.User;
import ru.fllcker.imagify.repositories.ISubscriptionsRepository;
import ru.fllcker.imagify.services.users.UsersService;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SubscriptionsService {
    private final ISubscriptionsRepository subscriptionsRepository;
    private final UsersService usersService;

    public Subscription create(String ae, String targetEmail) {
        User owner = usersService.findByEmail(ae)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!"));
        User target = usersService.findByEmail(targetEmail)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Target user not found!"));

        if (subscriptionsRepository.existsByOwnerEmailAndTargetEmail(owner.getEmail(), target.getEmail()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Subscription already exists!");

        Subscription subscription = new Subscription(owner, target);
        subscriptionsRepository.save(subscription);
        return subscription;
    }

    public List<Subscription> findUserSubscriptions(String ae) {
        return subscriptionsRepository.findSubscriptionsByOwnerEmail(ae);
    }

    public List<Subscription> findUserSubscribers(String ae) {
        return subscriptionsRepository.findSubscriptionsByTargetEmail(ae);
    }
}
