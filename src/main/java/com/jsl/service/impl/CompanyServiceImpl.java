package com.jsl.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.jsl.dto.CompanyRegistrationRequest;
import com.jsl.entity.Company;
import com.jsl.mapper.CompanyMapper;
import com.jsl.repository.CompanyRepository;
import com.jsl.service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {

	private final CompanyRepository repository;
	public CompanyServiceImpl(CompanyRepository repository) {
		this.repository = repository;
	}
	
	 private String generateRegistrationNumber(String company) {
		 String prefix = company.replaceAll("\\s", "").substring(0,4).toUpperCase();
		 String year = String.valueOf(LocalDateTime.now().getYear()) ;
		 String month = String.format("%02d", LocalDateTime.now().getMonthValue());
		 long count = repository.countByCompanyNamePrefixAndCreateMonth(prefix, year, month);
		 long serial = count+1;
		 
		 return prefix+year+month+String.format("%03d", serial);
	 }
	
	
	public void registerCompany(CompanyRegistrationRequest request) {
		
		if(repository.existsByEmail(request.getEmail())) {
			throw new RuntimeException("Email is already registered");
			
		}
		
		Company company = CompanyMapper.toEntity(request);
		String registrationNumber = generateRegistrationNumber(request.getCompanyName());
		company.setRegistrationNumber(registrationNumber);
		
		repository.save(company);
		
	}
}
