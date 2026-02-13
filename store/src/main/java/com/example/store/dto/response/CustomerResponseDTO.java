package com.example.store.dto.response;

import java.time.LocalDateTime;

import com.example.store.entity.User;
import com.example.store.enums.StatusCliente;

public record CustomerResponseDTO(Integer id,String nome,StatusCliente status, User user, LocalDateTime createAt) {

}
