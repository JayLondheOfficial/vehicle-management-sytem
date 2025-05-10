package com.jsl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import com.jsl.config.SecurityConfig;

@SpringBootApplication
@Import({SecurityConfig.class})
public class VehicleManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(VehicleManagementSystemApplication.class, args);
	}

}
