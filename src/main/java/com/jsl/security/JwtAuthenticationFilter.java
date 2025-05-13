package com.jsl.security;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.jsl.config.JwtUtil;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	private final JwtUtil jwtUtil;
	
	public JwtAuthenticationFilter(JwtUtil jwtUtil) {
		super();
		this.jwtUtil = jwtUtil;
	}




	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		//Get JWT from Autherization header
		
		String token = request.getHeader("Authorization");
		
		if(token!=null && token.startsWith("Bearer ")) {
			token = token.substring(7);
			
			try {
				Claims claims = jwtUtil.getClaims(token);
				GrantedAuthority authority = new SimpleGrantedAuthority(claims.get("role", String.class));
				
				Authentication authentication = new JwtAuthenticationToken(claims.getSubject(), List.of(authority));
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}catch(Exception e) {
				
			}
		}
		
		
		filterChain.doFilter(request, response);
	}

}
