package com.example.store.dto.response;

import java.time.LocalDateTime;

import com.example.store.enums.StatusCliente;

public record ProductResponseDTO(Long id, String nome, String descricao, StatusCliente status, LocalDateTime createdAt) {

}
