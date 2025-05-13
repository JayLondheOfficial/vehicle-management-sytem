package com.jsl.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/company")
public class ProfileController {
	
	@PreAuthorize("hasRole('USER')")
	@GetMapping("/profile")
	ResponseEntity<String> getProfile(){
		String email = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();	
		return ResponseEntity.ok("Welcome "+  email);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/admin/dashboard")
	ResponseEntity<String> adminDashboard(){
			
		return ResponseEntity.ok("Welcome Admin");
	}

}
