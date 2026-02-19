package com.example.store.mapper;

import com.example.store.dto.response.OrderDetailsResponseDTO;
import com.example.store.entity.OrderDetails;

public class OrderDetailsMapper {
	public static OrderDetailsResponseDTO toDto(OrderDetails items) {
		return new OrderDetailsResponseDTO(items.getProductId().getId(), 
											items.getPrecoUnitario(), 
											items.getQuantidade(), 
											items.getValorTotal());
	}

}
