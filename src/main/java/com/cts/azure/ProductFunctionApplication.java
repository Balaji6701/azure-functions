package com.cts.azure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.*")
public class ProductFunctionApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductFunctionApplication.class, args);
	}

}
