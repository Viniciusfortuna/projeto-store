package com.example.store.dto.response;

public record OrderDetailsResponseDTO(
	Long productId,
	Double precoUnitario,
	Double quantidade,
	Double valorTotal
) {

}
