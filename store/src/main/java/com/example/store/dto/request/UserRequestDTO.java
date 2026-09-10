package com.example.store.dto.request;

import java.util.List;

import com.example.store.enums.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRequestDTO(
		@Email
		@NotBlank(message = "O email deve ser Preenchido!")
		@NotNull
		String email,
		
		@NotBlank(message = "O login deve ser Preenchido!")
		@NotNull
		String login,
		
		@NotBlank(message = "A senha deve ser preenchida!")
		@NotNull
		String senha,
		
		@NotBlank(message = "O nome deve ser Preenchido!")
		@NotNull
		String nome,
		
		String telefone,
		
		@NotNull(message = "O role do usuário deve ser preenchido!")
		Role roleUsuario,
		
		@NotNull(message = "A lista de permissões precisa ser informada, mesmo que vazia!")
		List<Integer> permissaoIds
) {

}
