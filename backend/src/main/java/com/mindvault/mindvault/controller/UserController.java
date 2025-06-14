package com.mindvault.mindvault.controller;

import com.mindvault.mindvault.model.User;
import com.mindvault.mindvault.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> register(@RequestBody User user) {
        User saved = userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/{email}")
    public ResponseEntity<User> findByEmail(@PathVariable String email) {
        Optional<User> found = userService.findByEmail(email);
        return found.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
