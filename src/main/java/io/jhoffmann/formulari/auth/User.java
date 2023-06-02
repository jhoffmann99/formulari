package io.jhoffmann.formulari.auth;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import io.jhoffmann.formulari.subscription.SubscriptionEntity;
import io.jhoffmann.formulari.subscription.SubscriptionStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users", uniqueConstraints = {
    @UniqueConstraint(columnNames = "username"),
    @UniqueConstraint(columnNames = "email")
})
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  @Size(max = 50)
  private String username;

  @NotBlank
  @Size(max = 50)
  @Email
  private String email;

  @NotBlank
  @Size(max = 120)
  private String password;

  @Enumerated(EnumType.STRING)
  @ElementCollection(fetch = FetchType.EAGER)
  private Set<Role> roles = new HashSet<>();

  @OneToMany
  private Set<SubscriptionEntity> subscriptions = new HashSet<>();

  public User() {
  }

  public User(String email, String password) {
    this.username = email;
    this.email = email;
    this.password = password;
    addRolle(Role.ROLE_USER);
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Set<Role> getRoles() {
    return roles;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }

  public void addRolle(Role role) {
    roles.add(role);
  }

  public Set<SubscriptionEntity> getSubscriptions() {
    return subscriptions;
  }

  public void setSubscriptions(Set<SubscriptionEntity> subscriptions) {
    this.subscriptions = subscriptions;
  }

  public void addSubscription(SubscriptionEntity subscription) {
    this.subscriptions.add(subscription);
  }

  public Optional<SubscriptionEntity> getActiveSubscription() {
    return this.subscriptions.stream().filter(subscription -> subscription.getStatus() == SubscriptionStatus.ACTIVE).findFirst();
  }
}
