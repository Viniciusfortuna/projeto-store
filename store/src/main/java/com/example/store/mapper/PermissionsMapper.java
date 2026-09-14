package com.example.store.mapper;

import com.example.store.dto.request.PermissionsRequestDTO;
import com.example.store.dto.response.PermissionsResponseDTO;
import com.example.store.entity.Permissions;
import com.example.store.entity.User;

public class PermissionsMapper {
	
	public static PermissionsResponseDTO toDTO(Permissions permissions) {
		return new PermissionsResponseDTO(permissions.getId(), permissions.getNome(), permissions.getCreatedAt());
	}
	
	public static Permissions toEntity(PermissionsRequestDTO permissionsDto, User user, Boolean insercao) {
		Permissions permissions = new Permissions();
		permissions.setNome(permissionsDto.nome());
		
		if(insercao == true) {
			permissions.setUserGeracao(user);
		}
		else {
			permissions.setUserAtualizacao(user);
		}
		
		return permissions;
	}

}
