package ru.fllcker.imagify.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.fllcker.imagify.dto.CreateSubscriptionDto;
import ru.fllcker.imagify.models.Subscription;
import ru.fllcker.imagify.services.auth.AuthService;
import ru.fllcker.imagify.services.subscriptions.SubscriptionsService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/subscriptions")
public class SubscriptionsController {
    private final SubscriptionsService subscriptionsService;
    private final AuthService authService;

    @PostMapping("create")
    public ResponseEntity<Subscription> create(@RequestBody CreateSubscriptionDto createSubscriptionDto) {
        String ae = authService.getAuthInfo().getEmail();
        Subscription subscription = subscriptionsService.create(ae, createSubscriptionDto.getTarget());
        return ResponseEntity.ok(subscription);
    }

    @GetMapping("user/subscriptions")
    public ResponseEntity<List<Subscription>> getUserSubscriptions() {
        String ae = authService.getAuthInfo().getEmail();
        var subs = subscriptionsService.findUserSubscriptions(ae);
        return ResponseEntity.ok(subs);
    }

    @GetMapping("user/subscribers")
    public ResponseEntity<List<Subscription>> getUserSubscribers() {
        String ae = authService.getAuthInfo().getEmail();
        var subs = subscriptionsService.findUserSubscribers(ae);
        return ResponseEntity.ok(subs);
    }
}
