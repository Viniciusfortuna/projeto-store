package com.example.store.dto.request;

import com.example.store.entity.User;
import com.example.store.enums.StatusCliente;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CustomerRequestoDTO(
		@NotBlank
		@NotNull(message = "O nome deve ser preenchido!")
		String nome,
		
		@NotBlank
		@NotNull(message = "O CPF/CNPJ deve ser preenchido!")
		String cpfCnpj,
		
		@NotBlank
		@NotNull(message = "O telefone deve ser preenchido!")
		String telefone, 
		
		@NotNull(message = "O CEP deve ser preenchido!")
		Integer cep,
		
		StatusCliente status,
		
		Integer usuarioGeracao
) {

}
