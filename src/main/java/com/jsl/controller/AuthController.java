package com.jsl.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsl.dto.CompanyRegistrationRequest;
import com.jsl.dto.LoginRequest;
import com.jsl.entity.Company;
import com.jsl.repository.CompanyRepository;
import com.jsl.service.auth.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/companies/")
public class AuthController {

	private final AuthService service;

	public AuthController(AuthService service) {
		super();
		this.service = service;
	}
	
	@PostMapping("/register")
	ResponseEntity<String> register(@RequestBody @Valid CompanyRegistrationRequest request){
		service.registerCompany(request);
		return ResponseEntity.ok("Company registered successfully");
	}

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody @Valid LoginRequest request) {

		service.login(request);
		return ResponseEntity.ok("Login Successfull");

	}

}
