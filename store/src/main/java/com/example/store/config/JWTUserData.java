package com.example.store.config;

import lombok.Builder;

@Builder
public record JWTUserData(Long userId, String email, String role) {

}
