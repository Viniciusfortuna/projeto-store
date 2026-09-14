package com.example.store.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.store.dto.request.PermissionsRequestDTO;
import com.example.store.dto.response.PermissionsResponseDTO;
import com.example.store.entity.Permissions;
import com.example.store.entity.User;
import com.example.store.mapper.PermissionsMapper;
import com.example.store.repository.PermissionsRepository;

@Service
public class PermissionsServices {

	PermissionsRepository permissionsRepository;
	UserService userService;
	
	
	public PermissionsServices(PermissionsRepository permissionsRepository, UserService userService) {
		this.permissionsRepository = permissionsRepository;
		this.userService = userService;
	}
	
	public PermissionsResponseDTO salvar(PermissionsRequestDTO permissionsDto) {
		
		/*Busca usuário de atualização ou geração*/
		User usuario = userService.buscarUsuarioPorId(permissionsDto.usuario());
		
		Permissions permissions = PermissionsMapper.toEntity(permissionsDto, usuario, true);
		
		return PermissionsMapper.toDTO(permissionsRepository.save(permissions));
	}
	
	public PermissionsResponseDTO atualizarPorId(Integer id, PermissionsRequestDTO permissionsDto) {
		
		/*Busca permissão já existente*/
		Permissions permissions = permissionsRepository.findById(id).
				orElseThrow(() -> new RuntimeException("Permissão não encontrada!"));
		
		/*Busca usuário de atualização ou geração*/
		User usuario = userService.buscarUsuarioPorId(permissionsDto.usuario());
		
		if(!permissionsDto.nome().equals(permissions.getNome())) {
			permissions.setNome(permissionsDto.nome());
			permissions.setUserAtualizacao(usuario);
		}
		
		return PermissionsMapper.toDTO(permissionsRepository.save(permissions));
		
	}
	
	public String excluirPorId(Integer id) {
		Boolean exists = permissionsRepository.existsById(id);
		
		if(!exists) new RuntimeException("Não foi encontrada nenhuma permissão com o identificador informado!");
		
		permissionsRepository.deleteById(id);
		return "Permissão excluída com sucesso!";
	}
	
	
	public List<PermissionsResponseDTO> buscarTodos() {
		List<Permissions> permissions = permissionsRepository.findAll();
		
		return permissions.stream().map(PermissionsMapper::toDTO).toList();
	}
}
