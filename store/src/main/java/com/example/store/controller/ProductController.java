package com.example.store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.store.dto.request.ProductRequestDTO;
import com.example.store.dto.request.ProductUpdateRequestDTO;
import com.example.store.dto.response.ProductResponseDTO;
import com.example.store.services.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService service;
	
	
	@PostMapping
	public ResponseEntity<ProductResponseDTO> salvar(@Valid @RequestBody ProductRequestDTO dto){
		return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(dto));
	}
	
	@GetMapping
	public ResponseEntity<List<ProductResponseDTO>> buscarTodos(){
		return ResponseEntity.ok(service.listarTodos());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProductResponseDTO> buscarPorId(@PathVariable Long id){
		return ResponseEntity.ok(service.listarPorId(id));
	}
	
	@PutMapping
	public ResponseEntity<ProductResponseDTO> atualizarPorId(@RequestParam Long id, @Valid @RequestBody ProductUpdateRequestDTO dto){
		return ResponseEntity.status(HttpStatus.OK).body(service.atualizarPorId(id, dto));
	}
	
	@DeleteMapping
	public ResponseEntity<String> excluirPorId(@RequestParam Long id){
		service.excluirProdutoPorId(id);
		return ResponseEntity.ok("Produto excluído com sucesso!");
	}
}
