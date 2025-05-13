package com.jsl.config;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Component
public class JwtUtil {

	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.expiration}")
	private long expirationTime;

	private SecretKey secretKey;

	@PostConstruct
	public void init() {
		this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
	}

	// Generate JWT token
	public String generateToken(String email, String role) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("role", role);
		Instant now = Instant.now();
		Instant expiry = now.plusMillis(expirationTime);
		
		return Jwts.builder()
				.claims(claims)
				.subject(email)
				.issuedAt(Date.from(now))
				.expiration(Date.from(expiry))
				.signWith(secretKey)
				.compact();
		
		
	}

	

	public boolean isTokenValid(String token, String email) {
		return extractUserName(token).equals(email) && !isExpiredToken(token);
	}

	private boolean isExpiredToken(String token) {
		Date expiration = getExpiration(token);
		return expiration.before(new Date());
	}

	private Date getExpiration(String token) {
		return getClaims(token).getExpiration();
	}

	private String extractUserName(String token) {
		return extractClaimSubject(token);
	}

	private String extractClaimSubject(String token) {
		Claims claims = getClaims(token);
		return claims.get("sub", String.class);
	}

	public Claims getClaims(String token) {
		return Jwts
				.parser()
				.verifyWith(secretKey)
				.build()
				.parseSignedClaims(token)
				.getPayload();
	}

}
