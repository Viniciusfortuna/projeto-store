package com.example.store.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.store.dto.request.CustomerRequestoDTO;
import com.example.store.dto.request.CustomerUpdateRequestDTO;
import com.example.store.dto.response.CustomerResponseDTO;
import com.example.store.entity.Customer;
import com.example.store.entity.User;
import com.example.store.mapper.CustomerMapper;
import com.example.store.repository.CustomerRepository;

@Service
public class CustomerService {
	
	private CustomerRepository repository; 
	
	@Autowired
	private UserService userservice;
	
	public CustomerService(CustomerRepository repository) {
		this.repository = repository;
	}
	
	public CustomerResponseDTO salvar(CustomerRequestoDTO dto) {
		User user = userservice.buscarUsuarioPorId(dto.usuarioGeracao());
		
		Customer customer = CustomerMapper.toEntity(dto, user);
		repository.save(customer);
		
		return CustomerMapper.toDTO(customer);
	}
	
	public List<CustomerResponseDTO> buscarTodos(){
		return repository.findAll().stream().map(CustomerMapper::toDTO).toList();
	}
	
	public CustomerResponseDTO buscarPorId(Integer id){
		Customer customer = repository.findById(id).orElseThrow(()-> new RuntimeException("Cliente não encontrado! Verifique a Busca!")); 
		return CustomerMapper.toDTO(customer);
	}
	
	public Customer buscarPorIdEntity(Integer id){
		Customer customer = repository.findById(id).orElseThrow(()-> new RuntimeException("Cliente não encontrado! Verifique a Busca!")); 
		return customer;
	}
	
	public CustomerResponseDTO atualizarPorId(Integer id, CustomerUpdateRequestDTO dto) {
		Customer customer = repository.findById(id).orElseThrow(()-> new RuntimeException("Cliente não encontrado! Verifique a Busca!")); 
		
		if(dto.nome() != null && !dto.nome().isBlank()) customer.setNome(dto.nome());
		if(dto.cpfCnpj() != null && !dto.cpfCnpj().isBlank()) customer.setCpfCnpj(dto.cpfCnpj());
		if(dto.cep() != null) customer.setCep(dto.cep());
		if(dto.telefone() != null && !dto.telefone().isBlank()) customer.setTelefone(dto.telefone());
		if(dto.status() != null) customer.setStatus(dto.status());
		
	    repository.save(customer);
		return CustomerMapper.toDTO(customer);
	}
	
	public void excluirPorId(Integer id) {
		CustomerResponseDTO dto = buscarPorId(id);
		repository.deleteById(id);
	}
}
