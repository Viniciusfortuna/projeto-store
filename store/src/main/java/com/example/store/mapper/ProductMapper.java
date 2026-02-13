package com.example.store.mapper;

import com.example.store.dto.request.ProductRequestDTO;
import com.example.store.dto.response.ProductResponseDTO;
import com.example.store.entity.Product;
import com.example.store.entity.User;

public class ProductMapper {
	
	public static ProductResponseDTO toDTO(Product product) {
		return new ProductResponseDTO(product.getId(), 
									  product.getNome(), 
									  product.getDescricao(), 
									  product.getStatus(), 
									  product.getCreatedAt());
	}
	
	public static Product toEntity(ProductRequestDTO dto, User usuarioGeracao) {
		Product product = new Product();
		product.setNome(dto.nome());
		product.setDescricao(dto.descricao());
		product.setUnitario(dto.unitario());
		product.setStatus(dto.status());
		product.setUnidade(dto.unidade());
		product.setPesoBruto(dto.pesoBruto());
		product.setPesoLiquido(dto.pesoLiquido());
		product.setUsuarioGeracao(usuarioGeracao);
		/*product.setUsuarioAtualizacao(usuarioAtualizacao);*/	
		return product;
	}

}
