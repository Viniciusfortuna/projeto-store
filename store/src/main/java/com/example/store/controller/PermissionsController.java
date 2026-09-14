package com.example.store.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.store.dto.request.PermissionsRequestDTO;
import com.example.store.dto.response.PermissionsResponseDTO;
import com.example.store.services.PermissionsServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/permissions")
public class PermissionsController {
	
	PermissionsServices permissionsServices;
	
	public PermissionsController(PermissionsServices permissionsServices) {
		this.permissionsServices = permissionsServices;
	}
	
	@PostMapping
	public ResponseEntity<PermissionsResponseDTO> salvar(@Valid @RequestBody PermissionsRequestDTO permissionsDto){
		return ResponseEntity.status(200).body(permissionsServices.salvar(permissionsDto));
	}
	
	@PutMapping
	public ResponseEntity<PermissionsResponseDTO> atualizar(@RequestParam Integer id, @Valid @RequestBody PermissionsRequestDTO permissionsDto){
		return ResponseEntity.status(200).body(permissionsServices.atualizarPorId(id, permissionsDto));
	}
	
	@GetMapping
	public ResponseEntity<List<PermissionsResponseDTO>> buscarTodos(){
		return ResponseEntity.status(200).body(permissionsServices.buscarTodos());
	}
	
	@DeleteMapping
	public ResponseEntity<String> excluirPorId(@RequestParam Integer id){
		return ResponseEntity.ok(permissionsServices.excluirPorId(id));
	}

}
