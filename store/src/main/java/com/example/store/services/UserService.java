package com.example.store.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.store.dto.request.UserRequestDTO;
import com.example.store.dto.response.UserResponseDTO;
import com.example.store.entity.Permissions;
import com.example.store.entity.User;
import com.example.store.mapper.UserMapper;
import com.example.store.repository.PermissionsRepository;
import com.example.store.repository.UserRepository;

@Service
public class UserService {

	private final UserRepository repository;
	private final PermissionsRepository permissionsRepository;
	private final PasswordEncoder passwordEncoder;
	
	public UserService(UserRepository repository, PasswordEncoder passwordEncoder, PermissionsRepository permissionsRepository) {
		this.repository = repository;
		this.passwordEncoder = passwordEncoder;
		this.permissionsRepository = permissionsRepository;
	}
	
	public UserResponseDTO salvar(UserRequestDTO dto) {
		
		List<Permissions> permissions = dto.permissaoIds() == null
			    ? new ArrayList<>()
			    : permissionsRepository.findAllById(dto.permissaoIds());
		
		User user = UserMapper.toEntity(dto, permissions);
		
		user.setSenha(passwordEncoder.encode(user.getSenha()));
		
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
		user.setSenha(passwordEncoder.encode(dto.senha()));
		user.setTelefone(dto.telefone());
		user.setRoleUsuario(dto.roleUsuario());
		
		/*Se permissões forem informadas ele vai alterar as permissões do usuário em questão*/
		if((!dto.permissaoIds().isEmpty()) && (dto.permissaoIds() != null)) {
			List<Permissions> permissions = permissionsRepository.findAllById(dto.permissaoIds());
			user.setPermissoes(permissions);
		}
		
		
		User updated = repository.save(user);
		return UserMapper.toDTO(updated);
	}
}
