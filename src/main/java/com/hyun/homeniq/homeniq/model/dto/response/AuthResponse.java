package com.hyun.homeniq.homeniq.model.dto.response;

/**
 * 인증 응답 DTO
 */
public class AuthResponse {
    private String status;
    private UserResponse user;
    private String token;

    public AuthResponse() {}

    public AuthResponse(String status, UserResponse user, String token) {
        this.status = status;
        this.user = user;
        this.token = token;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UserResponse getUser() {
        return user;
    }

    public void setUser(UserResponse user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "AuthResponse{" +
                "status='" + status + '\'' +
                ", user=" + user +
                ", token='" + token + '\'' +
                '}';
    }
}
