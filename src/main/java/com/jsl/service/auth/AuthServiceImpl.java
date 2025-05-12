package com.jsl.service.auth;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jsl.config.JwtUtil;
import com.jsl.dto.CompanyRegistrationRequest;
import com.jsl.dto.LoginRequest;
import com.jsl.dto.LoginResponse;
import com.jsl.entity.Company;
import com.jsl.exception.ApiException;
import com.jsl.mapper.CompanyMapper;
import com.jsl.repository.CompanyRepository;

@Service
public class AuthServiceImpl implements AuthService {

	private final CompanyRepository repository;
	private final PasswordEncoder passwordEncoder;
	private final JwtUtil jwtUtil;

	public AuthServiceImpl(CompanyRepository repository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
		super();
		this.repository = repository;
		this.passwordEncoder = passwordEncoder;
		this.jwtUtil = jwtUtil;
	}

	private String generateRegistrationNumber(String company) {
		String prefix = company.replaceAll("\\s", "").substring(0, 4).toUpperCase();
		String year = String.valueOf(LocalDateTime.now().getYear());
		String month = String.format("%02d", LocalDateTime.now().getMonthValue());
		long count = repository.countByCompanyNamePrefixAndCreateMonth(prefix, year, month);
		long serial = count + 1;

		return prefix + year + month + String.format("%03d", serial);
	}

	public void registerCompany(CompanyRegistrationRequest request) {

		if (repository.existsByEmail(request.getEmail())) {
//			throw new RuntimeException("Email is already registered");
			throw new ApiException("Email is already registered");

		}

		Company company = CompanyMapper.toEntity(request);
		String registrationNumber = generateRegistrationNumber(request.getCompanyName());
		company.setRegistrationNumber(registrationNumber);
		company.setPassword(passwordEncoder.encode(company.getPassword()));

		repository.save(company);

	}

	public String login(LoginRequest request) {
		Company company = repository.findByEmail(request.getEmail())
				.orElseThrow(()-> new ApiException("Invalid email"));

		if (!passwordEncoder.matches(request.getPassword(), company.getPassword())) {
//			throw new RuntimeException("Invalid Password");
			throw new ApiException("Invalid Password");
		}
		
		return jwtUtil.generateToken(company.getEmail());

	}

}
