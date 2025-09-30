package com.mindvault.mindvault.dto.response;

public class UserSaltResponse {

    private String authSalt;
    private String encryptionSalt;

    public UserSaltResponse() {
    }

    public UserSaltResponse(String authSalt, String encryptionSalt) {
        this.authSalt = authSalt;
        this.encryptionSalt = encryptionSalt;
    }

    public String getAuthSalt() {
        return authSalt;
    }

    public void setAuthSalt(String authSalt) {
        this.authSalt = authSalt;
    }

    public String getEncryptionSalt() {
        return encryptionSalt;
    }

    public void setEncryptionSalt(String encryptionSalt) {
        this.encryptionSalt = encryptionSalt;
    }
}