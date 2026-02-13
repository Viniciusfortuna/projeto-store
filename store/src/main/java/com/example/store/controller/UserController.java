package com.example.store.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.store.dto.request.UserRequestDTO;
import com.example.store.dto.response.UserResponseDTO;
import com.example.store.entity.User;
import com.example.store.services.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
public class UserController {
	private final UserService service;
	
	public UserController(UserService service) {
		this.service = service;
	}
	
	@PostMapping
	public ResponseEntity<UserResponseDTO> saveUser(@RequestBody UserRequestDTO dto){
		return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(dto));
	}
	
	@GetMapping
	public ResponseEntity <List<UserResponseDTO>> buscarTodos(){
		return ResponseEntity.ok(service.buscarTodos());
	}
	
	@GetMapping("/name")
	public ResponseEntity<UserResponseDTO> buscarUsuarioPorNome(@RequestParam String name){
		return ResponseEntity.ok(service.buscarUsuarioPorNome(name));
	}
	
	@PutMapping 
	ResponseEntity<String> atualizarUsuarioPorId(@RequestParam Integer id, @RequestBody @Valid UserRequestDTO dto){
		service.atualizarUsuarioPorId(id, dto);
		return ResponseEntity.ok("Atualizado com sucesso!");
	}
	
	@DeleteMapping
	ResponseEntity<String> deletarUsuarioPorEmail(@RequestParam String email){
		service.deletarUsuarioPorEmail(email);
		return ResponseEntity.ok("Deletado com sucesso!");
	}
	
}
