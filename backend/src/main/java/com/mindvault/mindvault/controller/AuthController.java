package com.mindvault.mindvault.controller;

import com.mindvault.mindvault.dto.LoginRequest;
import com.mindvault.mindvault.model.User;
import com.mindvault.mindvault.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserRepository userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        Optional<User> optional = userRepo.findByEmail(request.getEmail());

        if (optional.isEmpty())
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");

        User user = optional.get();
        boolean matches = passwordEncoder.matches(request.getPassword(), user.getPassword());

        if (!matches)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");

        // success – for now return basic profile (no password)
        return ResponseEntity.ok(
                new UserProfile(user.getFirstName(), user.getLastName(), user.getEmail())
        );
    }

    // minimalist inner record to hide password
    record UserProfile(String firstName, String lastName, String email) {}
}
