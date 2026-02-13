package com.example.store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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

import com.example.store.dto.request.CustomerRequestoDTO;
import com.example.store.dto.request.CustomerUpdateRequestDTO;
import com.example.store.dto.response.CustomerResponseDTO;
import com.example.store.services.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired /*teste*/
	private CustomerService service;
	
	@PostMapping
	public ResponseEntity<CustomerResponseDTO> salvar(@RequestBody CustomerRequestoDTO dto){
		return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(dto));
	}
	
	@GetMapping
	public ResponseEntity<List<CustomerResponseDTO>> buscarTodos(){
		return ResponseEntity.ok(service.buscarTodos());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CustomerResponseDTO> buscarPorId(@PathVariable Integer id){
		return ResponseEntity.ok(service.buscarPorId(id));
	}
	
	@PutMapping
	public ResponseEntity<CustomerResponseDTO> atualizarPorId(@RequestParam Integer id, @RequestBody CustomerUpdateRequestDTO dto){
		return ResponseEntity.status(HttpStatus.OK).body(service.atualizarPorId(id, dto));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> excluirPorId(@PathVariable Integer id){
		service.excluirPorId(id);
		return ResponseEntity.ok("Excluído com sucesso!");
	}
	

}
