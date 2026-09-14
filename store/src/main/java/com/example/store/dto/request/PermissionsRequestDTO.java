package com.example.store.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record PermissionsRequestDTO(
		@NotNull
		@NotEmpty(message = "O descrição da permissão deve ser informada!")
		String nome,
		
		@NotNull(message = "Usuário é obrigatório!")
		Integer usuario
) {

}
