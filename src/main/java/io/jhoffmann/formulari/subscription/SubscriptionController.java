package io.jhoffmann.formulari.subscription;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/subscription")
public class SubscriptionController {
    private final SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> addSubscription(@RequestBody SubscriptionRequestDto data, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        subscriptionService.addSubscription(data, userDetails);

        return ResponseEntity.ok().build();
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    public ResponseEntity<SubscriptionResponseDto> getActiveSubscription(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        Optional<SubscriptionEntity> optSubscription = subscriptionService.getActiveSubscription(userDetails);

        if (optSubscription.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        SubscriptionEntity subscription = optSubscription.get();

        SubscriptionResponseDto response = new SubscriptionResponseDto();
      
        response.setActivatedAt(subscription.getActivatedAt());
        response.setStatus(subscription.getStatus());

        return ResponseEntity.ok(response);
    }
}
