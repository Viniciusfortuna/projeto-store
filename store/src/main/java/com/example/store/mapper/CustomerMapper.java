package com.example.store.mapper;

import com.example.store.dto.request.CustomerRequestoDTO;
import com.example.store.dto.response.CustomerResponseDTO;
import com.example.store.entity.Customer;
import com.example.store.entity.User;

public class CustomerMapper {
	
	public static CustomerResponseDTO toDTO(Customer customer) {
		return new CustomerResponseDTO(customer.getId(), customer.getNome(), customer.getStatus(), customer.getUsuarioGeracao(), customer.getCreatedAt());
	}
	
	public static Customer toEntity(CustomerRequestoDTO dto, User user) {
		Customer customer = new Customer();
		
		/*-------- Monta Objeto --------*/
		customer.setNome(dto.nome());
		customer.setCpfCnpj(dto.cpfCnpj());
		customer.setCep(dto.cep());
		customer.setTelefone(dto.telefone());
		customer.setStatus(dto.status());
		customer.setUsuarioGeracao(user);
		
		return customer;
	}
}
