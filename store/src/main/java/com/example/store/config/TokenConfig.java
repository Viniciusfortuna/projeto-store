package com.example.store.config;

import java.time.Instant;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.store.entity.User;

@Component
public class TokenConfig {
	
	private String secret = "secret";
	
	public String generateToken(User user) {
		
		Algorithm algorithm = Algorithm.HMAC256(secret);
		
		return JWT.create()
				.withClaim("user", user.getId())
				.withClaim("role", user.getRoleUsuario().name())
				.withSubject(user.getEmail())
				.withExpiresAt(Instant.now().plusSeconds(86400))
				.withIssuedAt(Instant.now())
				.sign(algorithm);
		
	}

	public Optional<JWTUserData> validateToken(String token) {
		// TODO Auto-generated method stub
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			DecodedJWT decoded = JWT.require(algorithm).build().verify(token);
			
			return Optional.of(JWTUserData.builder()
					.userId(decoded.getClaim("user").asLong())
					.role(decoded.getClaim("role").asString())
					.email(decoded.getSubject())
					.build());
			
		} catch (JWTVerificationException e) {
			// TODO: handle exception
			return Optional.empty();
		}
	}

}
