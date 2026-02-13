package com.example.store.mapper;

import com.example.store.dto.request.UserRequestDTO;
import com.example.store.dto.response.UserResponseDTO;
import com.example.store.entity.User;

public class UserMapper {
	
	public static UserResponseDTO toDTO(User user) {
		
		return new UserResponseDTO(user.getId(),
								   user.getNome(), 
								   user.getEmail());
	}
	
	public static User toEntity(UserRequestDTO dto) {
		User user = new User();
		user.setNome(dto.nome());
		user.setLogin(dto.login());
		user.setEmail(dto.email());
		user.setSenha(dto.senha());
		user.setTelefone(dto.telefone());
		return user;
	}

}
