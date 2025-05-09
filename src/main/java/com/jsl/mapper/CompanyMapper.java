package com.jsl.mapper;

import com.jsl.dto.CompanyRegistrationRequest;
import com.jsl.entity.Company;

public class CompanyMapper {
	
	public static Company toEntity(CompanyRegistrationRequest request) {
		Company company = new Company();
		company.setCompanyName(request.getCompanyName());
		company.setEmail(request.getEmail());
		company.setPassword(request.getPassword());
		company.setAddressLine1(request.getAddressLine1());
		company.setAddressLine2(request.getAddressLine2());
		company.setContactFirstName(request.getContactFirstName());
		company.setContactLastName(request.getContactLastName());
		company.setCity(request.getCity());
		company.setCountry(request.getCountry());
		company.setPhone(request.getPhone());

		
		return company;
	}
	
	
	

}
