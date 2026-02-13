package com.example.store.dto.request;

import com.example.store.enums.StatusCliente;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record ProductUpdateRequestDTO(
		String nome,		
		String descricao,
		Double unitario,
		StatusCliente status,
		String unidade,
		Double pesoLiquido,
		Double pesoBruto,
		@NotNull(message = "O usuário de ALTERAÇÃO deve ser informado!")
		Integer usuarioAlteracao
) {

}
