package com.mindvault.mindvault.dto.mapper;

import com.mindvault.mindvault.dto.response.AuthResponse;
import com.mindvault.mindvault.dto.response.UserResponse;
import com.mindvault.mindvault.dto.response.UserSaltResponse;
import com.mindvault.mindvault.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserResponse toUserResponse(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setEmail(user.getEmail());
        response.setCreatedAt(user.getCreatedAt());
        return response;
    }

    public UserSaltResponse toSaltResponse(User user) {
        UserSaltResponse response = new UserSaltResponse();
        response.setAuthSalt(user.getAuthSalt());
        response.setEncryptionSalt(user.getEncryptionSalt());
        return response;
    }

    public AuthResponse toAuthResponse(User user, String token, String refreshToken) {
        AuthResponse response = new AuthResponse();
        response.setUserId(user.getId());
        response.setEmail(user.getEmail());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setToken(token);
        response.setRefreshToken(refreshToken);
        return response;
    }
}