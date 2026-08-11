package com.example.store.specification;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.data.jpa.domain.Specification;

import com.example.store.dto.request.ProductFilterRequest;
import com.example.store.entity.Product;

public class ProductSpecification {
	
	public static Specification<Product> withFilter(ProductFilterRequest filter){
		return Specification.where(idContains(filter.codigo()))
				.and(descricaoContains(filter.descricao()))
				.and(nomeContains(filter.nome()))
				.and(intervalCreatedIni(filter.dataGeracaoInicio()))
				.and(intervalCreatedFim(filter.dataGeracaoFim()));
	}
	
	private static Specification<Product> idContains(Long id){
		return (root, query, cb) -> { 
			if(id == null || id == 0) return null;
			
			return cb.equal(root.get("id"), id);  /*Campo a ser comparado, valor a ser comparado*/
		};
	}
	
	private static Specification<Product> descricaoContains(String descricao){
		return (root, query, cb) -> { 
			if(descricao == null || descricao.isBlank()) return null;
			
			return cb.like(cb.lower(root.get("descricao")), '%'+descricao+'%');  /*Campo a ser comparado, valor a ser comparado*/
		};
	}
	
	private static Specification<Product> nomeContains(String nome){
		return (root, query, cb) -> { 
			if(nome == null || nome.isBlank()) return null;
			
			return cb.like(cb.lower(root.get("nome")), '%'+nome+'%');  /*Campo a ser comparado, valor a ser comparado*/
		};
	}
	
	private static Specification<Product> intervalCreatedIni(LocalDate dataInicio){
		return (root, query, cb) -> {
			if(dataInicio == null) return null;
			
			LocalDateTime dataInicioTime = dataInicio.atStartOfDay(); 
			return cb.greaterThanOrEqualTo(root.get("createdAt"), dataInicioTime);
		};
	}
	
	private static Specification<Product> intervalCreatedFim(LocalDate dataFim){
		return (root, query, cb) -> {
			if(dataFim == null) return null;
			
			LocalDateTime dataFimTime = dataFim.atTime(LocalTime.MAX);
			return cb.lessThanOrEqualTo(root.get("createdAt"), dataFimTime);
		};
	}
	
}
