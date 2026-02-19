package com.example.store.dto.request;

import jakarta.validation.constraints.NotNull;

public record OrderDetailsRequestDTO(
	@NotNull(message = "O código do produto precisa ser informado!")
	Long productId,
	
	@NotNull(message = "O preço unitário precisa ser informado!")
	Double precoUnitario,
	
	@NotNull(message = "A quantidade precisa ser informada!")
	Double quantidade,
	
	@NotNull(message = "O valor total precisa ser informado!")
	Double valorTotal
) {

}
