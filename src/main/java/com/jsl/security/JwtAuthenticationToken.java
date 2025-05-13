package com.jsl.security;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;


public class JwtAuthenticationToken extends  AbstractAuthenticationToken {
	
	public JwtAuthenticationToken(String principle, Collection<? extends GrantedAuthority> authorities) {
		super(authorities);
		this.principle = principle;
	    setAuthenticated(true);
	}

	private final String principle;

//	public JwtAuthenticationToken(String principle) {
//		super(null);
//	    this.principle = principle;
//	    setAuthenticated(true);
//	}

	@Override
	public Object getCredentials() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getPrincipal() {
		return this.principle;
	}

}
