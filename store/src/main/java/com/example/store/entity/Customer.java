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
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "clientes")
@Getter
@Setter
@Builder
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	@NotBlank
	private String nome;
	
	@NotNull
	@NotBlank
	private String cpfCnpj;
	
	private String telefone;
	
	private Integer cep;
	
	@Enumerated(EnumType.STRING)
	private StatusCliente status;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User usuarioGeracao;
	
	private LocalDateTime createdAt;
	
	@PrePersist
	public void atualizarData() {
		this.createdAt = LocalDateTime.now();
	}
	
}
