package com.jsl.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsl.dto.CompanyRegistrationRequest;
import com.jsl.service.CompanyService;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {
	
	
	private final CompanyService service;
	

	
	public CompanyController(CompanyService service) {
		super();
		this.service = service;
	}



	@PostMapping("/register")
	ResponseEntity<String> register(@RequestBody CompanyRegistrationRequest request){
		service.registerCompany(request);
		return ResponseEntity.ok("Company registered successfully");
	}

}
