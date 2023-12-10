package com.assignment.cityinformation;

import com.assignment.cityinformation.config.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
public class CityInformationApplication {

	public static void main(String[] args) {
		SpringApplication.run(CityInformationApplication.class, args);
	}

}
