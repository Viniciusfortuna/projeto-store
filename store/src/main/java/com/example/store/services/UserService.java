package com.example.store.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.store.dto.request.UserRequestDTO;
import com.example.store.dto.response.UserResponseDTO;
import com.example.store.entity.User;
import com.example.store.mapper.UserMapper;
import com.example.store.repository.UserRepository;

@Service
public class UserService {

	private final UserRepository repository;
	
	public UserService(UserRepository repository) {
		this.repository = repository;
	}
	
	public UserResponseDTO salvar(UserRequestDTO dto) {
		
		User user = UserMapper.toEntity(dto);
		
		User created = repository.save(user);
		return UserMapper.toDTO(created);
	}
	
	public User buscarUsuarioPorId(Integer id) {
		User user = repository.findById(id).orElseThrow(()-> new RuntimeException("Usuário não encontrado! Verique a busca!"));
		return user;
	}
	
	public List<UserResponseDTO> buscarTodos() {
		List<User> user = repository.findAll();
		
		return user.stream().map(UserMapper::toDTO).toList(); /*----- formato padrão para retorno de lista ------*/
	}
	
	public UserResponseDTO buscarUsuarioPorNome(String nome) {
		
		User user = repository.findByNome(nome).orElseThrow(
				()-> new RuntimeException("nome não encontrado")
		);
		
		return UserMapper.toDTO(user);
	}
	
	public void deletarUsuarioPorEmail(String email) {
		repository.deleteByEmail(email);
	}
	
	public UserResponseDTO atualizarUsuarioPorId(Integer id, UserRequestDTO dto) {
		User user = repository.findById(id).orElseThrow(()-> new RuntimeException("Usuário não encontrado!"));
		
		user.setEmail(dto.email());
		user.setLogin(dto.login());
		user.setNome(dto.nome());
		user.setSenha(dto.senha());
		user.setTelefone(dto.telefone());
		
		User updated = repository.save(user);
		return UserMapper.toDTO(updated);
	}
}
