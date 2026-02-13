package com.example.store.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRequestDTO(
		@Email
		@NotBlank(message = "O email deve ser Preenchido!")
		@NotNull
		String email,
		
		@Email
		@NotBlank(message = "O login deve ser Preenchido!")
		@NotNull
		String login,
		
		@Email
		@NotBlank(message = "A senha deve ser preenchida!")
		@NotNull
		String senha,
		
		@Email
		@NotBlank(message = "O nome deve ser Preenchido!")
		@NotNull
		String nome,
		
		String telefone
) {

}
