package io.jhoffmann.formulari.auth;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.jhoffmann.formulari.exception.NotFoundException;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  private final UserService userService;

  public AuthController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

    Authentication authentication = null;

    try {
      authentication = authenticationManager
          .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
    } catch (Exception e) {
      e.printStackTrace();
    }

    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

    List<String> roles = userDetails.getAuthorities().stream()
        .map(item -> item.getAuthority())
        .collect(Collectors.toList());

    Optional<User> optUser = userService.findUserByEmail(userDetails.getUsername());

    if (optUser.isEmpty()) {
      throw new NotFoundException("User not found");
    }

    User user = optUser.get();

    String token = jwtUtils.generateTokenFromUsername(user.getSub());

    UserInfoResponse response = new UserInfoResponse(userDetails.getEmail(), token, roles);

    return ResponseEntity.ok(response);
  }

  @PostMapping("/signup")
  @PermitAll
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {

    if (userService.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
    }

    // Create new user's account
    User user = new User(
        signUpRequest.getEmail(),
        encoder.encode(signUpRequest.getPassword()));

    userService.addUser(user);

    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
  }

}