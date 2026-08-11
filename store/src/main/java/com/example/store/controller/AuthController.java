package com.example.store.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.store.config.TokenConfig;
import com.example.store.dto.request.LoginRequest;
import com.example.store.dto.response.LoginResponse;
import com.example.store.entity.User;
import com.example.store.repository.UserRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	private final UserRepository userRepository;
	private final AuthenticationManager authenticationManager; /*Faz todo o gerenciamento da autenticação*/
	private final TokenConfig tokenConfig;
	
	public AuthController(UserRepository userRepository, AuthenticationManager authenticationManager, TokenConfig tokenConfig) {
		this.userRepository = userRepository;
		this.authenticationManager = authenticationManager;
		this.tokenConfig = tokenConfig;
	}
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request){
		UsernamePasswordAuthenticationToken userAndPass = new UsernamePasswordAuthenticationToken(request.login(), request.password());
		Authentication authentication = authenticationManager.authenticate(userAndPass);
		
		/*Retorna o Token para o usuário*/
		User user = (User) authentication.getPrincipal();
		String token = tokenConfig.generateToken(user);
		
		return ResponseEntity.ok(new LoginResponse(token));
	}

}
