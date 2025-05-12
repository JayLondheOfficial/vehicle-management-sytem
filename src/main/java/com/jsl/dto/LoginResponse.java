package com.jsl.dto;

public class LoginResponse {
	private String token;
	private String email;
	public LoginResponse(String email, String token) {
		super();
		this.token = token;
		this.email = email;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	

}
