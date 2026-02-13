package com.example.store.dto.request;

import com.example.store.enums.StatusCliente;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductRequestDTO(
	@NotNull(message = "O nome deve ser preenchido!")
	@NotBlank
	String nome,
	
	@NotNull(message = "A descricao deve ser preenchida!")
	@NotBlank
	String descricao,
	
	@NotNull(message = "O valor unitário deve ser preenchido!")
	Double unitario,
	
	@NotNull(message = "O status deve ser preenchido!")
	StatusCliente status,
	
	@NotNull(message = "A unidade de medida deve ser preenchida!")
	@NotBlank
	String unidade,
	
	Double pesoLiquido,
	
	Double pesoBruto,
	
	@NotNull(message = "O usuário de geração deve ser informado!")
	Integer usuarioGeracao

) {

}
