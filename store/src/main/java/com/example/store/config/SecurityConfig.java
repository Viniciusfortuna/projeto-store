package com.example.store.config;

import org.springframework.boot.web.servlet.DispatcherType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.http.HttpMethod;

@Configuration /*Reconhece a Anotação Bean de Configuração*/
@EnableWebSecurity /*Permite aos desenvolvedores alterarem a política de acesso*/
public class SecurityConfig {
	
	private final SecurityFilter securityFilter;
	
	public SecurityConfig(SecurityFilter securityFilter) {
		this.securityFilter = securityFilter;
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) {
		return http
				.csrf(csrf -> csrf.disable())
				.cors(cors -> cors.configure(http))
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) /*Cada requisição vai trazer a seu próprio token*/
				.authorizeHttpRequests(authorize -> authorize
						.requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
						//.requestMatchers(HttpMethod.POST, "/users").permitAll()
						.requestMatchers("/users/**").hasRole("ADMIN")
						.anyRequest().authenticated())
				.addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
				.build();
				
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
	        return authConfig.getAuthenticationManager();
	}
	
	@Bean /*Automaticamente vai encontrar para fazer o encoder da senha no service*/
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
