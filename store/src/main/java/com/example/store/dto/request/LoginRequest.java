package com.example.store.dto.request;

import jakarta.validation.constraints.NotEmpty;

public record LoginRequest(@NotEmpty(message = "Usuário é obrigatório!") String login, @NotEmpty(message = "Senha é obrigatória!") String password) {

}
