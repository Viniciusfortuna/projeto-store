package com.example.store.mapper;

import com.example.store.dto.request.OrderDetailsRequestDTO;
import com.example.store.dto.request.OrderRequestDTO;
import com.example.store.dto.response.OrderResponseDTO;
import com.example.store.entity.Customer;
import com.example.store.entity.Order;
import com.example.store.entity.OrderDetails;
import com.example.store.entity.User;

public class OrderMapper {
	
	public static OrderResponseDTO toDTO(Order order) {
		
		return new OrderResponseDTO(order.getCustomer().getId(), 
									order.getValorTotal(),
									order.getItems().stream().map(OrderDetailsMapper::toDto).toList());
		
	}
	
	/*
	public static Order toEntity(OrderRequestDTO dto, User usuarioGeracao, Customer customer) {
		Order order = new Order();
		
		order.setCustomer(customer);
		order.setPesoBruto(dto.pesoBruto());
		order.setPesoLiquido(dto.pesoLiquido());
		order.setValorTotal(dto.valorTotal());
		order.setUsuarioGeracao(usuarioGeracao);
		
		if(dto.items() != null) {
			for(OrderDetailsRequestDTO dtoDetails : dto.items()) {
				OrderDetails orderDetails = new OrderDetails(); 
				orderDetails.setProductId(null);
				
			}
		}
		
	}*/
}
