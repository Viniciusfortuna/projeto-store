package com.example.store.config;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleValidacao(MethodArgumentNotValidException ex){
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("status", HttpStatus.BAD_REQUEST.value());
		response.put("timestamp", LocalDateTime.now());
		response.put("erros",
	        ex.getBindingResult()
	            .getFieldErrors()
	            .stream()
	            .map(error -> Map.of(
	                "campo", error.getField(),
	                "mensagem", error.getDefaultMessage()
	            ))
	            .collect(Collectors.toUnmodifiableList())
	    );
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}
	
	@ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleRuntimeException(RuntimeException ex) {

        Map<String, Object> response = new HashMap<>();

        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("timestamp", LocalDateTime.now());
        response.put("mensagem", ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

}
