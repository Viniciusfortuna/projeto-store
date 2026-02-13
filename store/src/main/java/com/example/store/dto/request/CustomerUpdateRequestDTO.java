package com.example.store.dto.request;

import com.example.store.enums.StatusCliente;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CustomerUpdateRequestDTO(
		String nome,
		String cpfCnpj,
		String telefone, 
		Integer cep,
		StatusCliente status
) {

}
