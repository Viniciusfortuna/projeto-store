package com.example.store.config;

import java.awt.List;
import java.io.IOException;
import java.util.Optional;

import org.apache.logging.log4j.util.Strings;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component /*Responsável por Filtrar e Reconhecer o Token*/
public class SecurityFilter extends OncePerRequestFilter  {

	private final TokenConfig tokenConfig;

	public SecurityFilter (TokenConfig tokenConfig) {
		this.tokenConfig = tokenConfig;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String authorizationHeader = request.getHeader("Authorization");
		if(Strings.isNotEmpty(authorizationHeader) && authorizationHeader.startsWith("Bearer")) {
			String token = authorizationHeader.substring("Bearer ".length()); /*Armazenar apenas o que vem depois de bearer*/
			Optional<JWTUserData> optUser = tokenConfig.validateToken(token);
			
			if(optUser.isPresent()) {
				JWTUserData userData = optUser.get();
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userData,null, java.util.List.of(new SimpleGrantedAuthority(userData.role())));
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
			filterChain.doFilter(request, response);
		}
		else {
			filterChain.doFilter(request, response);
		}
		
	}

}
