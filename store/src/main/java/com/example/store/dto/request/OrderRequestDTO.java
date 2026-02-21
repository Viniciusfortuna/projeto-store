package com.example.store.dto.request;

import java.util.List;

import jakarta.validation.constraints.NotNull;

public record OrderRequestDTO(
		@NotNull(message = "O código do cliente deve ser preenchido!")
		Integer customerCode,
		
//		@NotNull(message = "O valor total precisa ser informado!")
//		Double valorTotal,
		
//		@NotNull(message = "O peso bruto precisa ser informado!")
//		Double pesoBruto,
//		
//		@NotNull(message = "O peso liquído precisa ser informado!")
//		Double pesoLiquido,
		
		@NotNull(message = "Os itens precisam ser informados!")
		List<OrderDetailsRequestDTO> items,
		
		@NotNull(message = "O usuário de geração precisa ser informado!")
		Integer usuarioGeracao
) {

}
