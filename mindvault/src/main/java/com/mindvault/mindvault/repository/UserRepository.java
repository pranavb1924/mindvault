package com.mindvault.mindvault.repository;

import com.mindvault.mindvault.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);   // we’ll use this at login
    boolean existsByEmail(String email);
}
