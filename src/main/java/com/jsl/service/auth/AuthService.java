package com.jsl.service.auth;

import com.jsl.dto.CompanyRegistrationRequest;
import com.jsl.dto.LoginRequest;

public interface AuthService {
	void registerCompany(CompanyRegistrationRequest request);
	void login(LoginRequest request);

}
