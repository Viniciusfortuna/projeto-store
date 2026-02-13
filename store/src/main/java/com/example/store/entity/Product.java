package com.example.store.entity;

import java.time.LocalDateTime;

import com.example.store.enums.StatusCliente;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "produtos")
@Builder
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String nome;
	
	@NotNull
	private String descricao;
	
	@NotNull
	private Double unitario;
	
	@Enumerated(EnumType.STRING)
	private StatusCliente status;
	
	@NotNull
	private String unidade;
	
	private Double pesoLiquido;
	
	private Double pesoBruto;
	
	private LocalDateTime createdAt;
	
	private LocalDateTime updatedAt;
	
	@ManyToOne
	@JoinColumn(name = "user_geracao_id")
	private User usuarioGeracao;
	
	@ManyToOne
	@JoinColumn(name = "user_atualizacao_id")
	private User usuarioAtualizacao;
	
	@PrePersist
	public void createdData() {
		this.createdAt = LocalDateTime.now();
	}
	
	@PreUpdate
	public void updatedData() {
		this.updatedAt = LocalDateTime.now();
	}
}
