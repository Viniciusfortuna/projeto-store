package com.example.store.dto.response;

import java.util.List;

import com.example.store.dto.request.OrderDetailsRequestDTO;
import com.example.store.entity.Customer;

public record OrderResponseDTO(
	Integer customerCode,
	Double valorTotal,
	List<OrderDetailsResponseDTO> items
) {


}
