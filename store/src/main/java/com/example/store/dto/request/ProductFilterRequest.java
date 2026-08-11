package com.example.store.dto.request;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ProductFilterRequest(
		Long codigo,
		String nome,
		String descricao,
		LocalDate dataGeracaoInicio,
		LocalDate dataGeracaoFim
		) {

}
