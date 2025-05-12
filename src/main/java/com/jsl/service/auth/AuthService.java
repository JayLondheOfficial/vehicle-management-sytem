package com.jsl.service.auth;

import com.jsl.dto.CompanyRegistrationRequest;
import com.jsl.dto.LoginRequest;
import com.jsl.dto.LoginResponse;

public interface AuthService {
	void registerCompany(CompanyRegistrationRequest request);
	
	String login(LoginRequest request);

}
