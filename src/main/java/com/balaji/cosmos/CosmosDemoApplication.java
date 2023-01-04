package com.balaji.cosmos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.azure.spring.data.cosmos.core.mapping.EnableCosmosAuditing;

@SpringBootApplication
@EnableCosmosAuditing
public class CosmosDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CosmosDemoApplication.class, args);
	}

}
