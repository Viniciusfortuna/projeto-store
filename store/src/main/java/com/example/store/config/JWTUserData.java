package com.example.store.config;



import java.util.List;

import lombok.Builder;

@Builder
public record JWTUserData(Long userId, String email, String role, List<String>permissions) {

}
