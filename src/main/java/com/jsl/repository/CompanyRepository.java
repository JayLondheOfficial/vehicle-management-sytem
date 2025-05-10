package com.jsl.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jsl.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {

	boolean existsByEmail(String email);
	Optional<Company> findByEmail(String email);

	@Query("SELECT COUNT(c) FROM Company c WHERE UPPER(SUBSTRING(c.companyName, 1,4)) = :prefix AND FUNCTION (YEAR, c.createdAt) = :year AND FUNCTION(MONTH, c.createdAt) = :month")
	long countByCompanyNamePrefixAndCreateMonth(@Param("prefix") String prefix, @Param("year") String year, @Param("month") String month);

	

}
