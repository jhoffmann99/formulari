package io.jhoffmann.formulari.subscription;


import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jhoffmann.formulari.auth.User;
import io.jhoffmann.formulari.auth.UserService;
import io.jhoffmann.formulari.exception.NotFoundException;

@Service
public class SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;
    private final UserService userService;

    public SubscriptionService(SubscriptionRepository subscriptionRepository, UserService userService) {
        this.subscriptionRepository = subscriptionRepository;
        this.userService = userService;
    }

    public Optional<SubscriptionEntity> getActiveSubscription(UserDetails userDetails) {
        Optional<User> optUser = userService.findUserByUsername(userDetails.getUsername());
        
        if (optUser.isEmpty()) {
            throw new NotFoundException("User not found");
        }

        User user = optUser.get();

        return subscriptionRepository.findActiveSubscription(user);
    }


}
