// src/main/java/com/mindvault/mindvault/dto/UserProfile.java
package com.mindvault.mindvault.dto;

public record UserProfile(
        Long id,
        String firstName,
        String lastName,
        String email
) {}
