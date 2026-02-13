package com.example.store.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.store.dto.request.ProductRequestDTO;
import com.example.store.dto.request.ProductUpdateRequestDTO;
import com.example.store.dto.response.ProductResponseDTO;
import com.example.store.entity.Product;
import com.example.store.entity.User;
import com.example.store.mapper.ProductMapper;
import com.example.store.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repository;
	
	@Autowired
	private UserService userService;
	
	public ProductResponseDTO salvar(ProductRequestDTO dto) {
		
		User user = userService.buscarUsuarioPorId(dto.usuarioGeracao()); 
		
		Product product = ProductMapper.toEntity(dto, user);
		
		return ProductMapper.toDTO(repository.save(product)); 
	}
	
	public List<ProductResponseDTO> listarTodos() {
		return repository.findAll().stream().map(ProductMapper::toDTO).toList();
	}
	
	public ProductResponseDTO listarPorId(Long id) {
		Product product = repository.findById(id).orElseThrow(()-> new RuntimeException("Produto não encontrado! Verifique a busca!"));
		return ProductMapper.toDTO(product); 
	}
	
	public void excluirProdutoPorId(Long id) {
		listarPorId(id);
		repository.deleteById(id);
	}
	
	public ProductResponseDTO atualizarPorId(Long id, ProductUpdateRequestDTO dto) {
		Product product = repository.findById(id).orElseThrow(()-> new RuntimeException("Produto não cadastrado!"));
		User userAlteracao = userService.buscarUsuarioPorId(dto.usuarioAlteracao());
		
		/*----------- Peso Bruto não pode ser menor que o líquido -----------*/
		
		if(dto.nome() != null && !dto.nome().isBlank()) product.setNome(dto.nome());

		if(dto.descricao() != null && !dto.descricao().isBlank()) product.setDescricao(dto.descricao());

		if(dto.unitario() != null) product.setUnitario(dto.unitario());

		if(dto.status() != null) product.setStatus(dto.status());

		if(dto.unidade() != null && !dto.unidade().isBlank()) product.setUnidade(dto.unidade());

		if(dto.pesoLiquido() != null) product.setPesoLiquido(dto.pesoLiquido());

		if(dto.pesoBruto() != null) product.setPesoBruto(dto.pesoBruto());
		
		if(dto.usuarioAlteracao() != null) product.setUsuarioAtualizacao(userAlteracao);
		
		Double pesoBruto = product.getPesoBruto();
		Double pesoLiquido = product.getPesoLiquido();
		
		if(pesoBruto == null) pesoBruto = 0.00;
		if(pesoLiquido == null) pesoLiquido = 0.00;
		
		if(pesoBruto == 0.00 || pesoLiquido == 0.00) throw new RuntimeException("Existem pesos zerados, verifique!");
		
		if(pesoBruto < pesoLiquido) throw new RuntimeException("Peso liquído não pode ser maior que o peso bruto!");
		
		return ProductMapper.toDTO(repository.save(product)); 
	}
}
