package com.example.functions;

import java.util.List;
import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.example.models.Product;

@Configuration
public class ProductFunction {

	private RestTemplate restTemplate = new RestTemplate();

	@Bean("productGet")
	public Function<String, Object> productGet() {
		return id -> {
			try {
				Product product = restTemplate.getForEntity("http://localhost:8080/products/" + id, Product.class).getBody();				
				return product; 
			}catch (Exception e) {
				return ResponseEntity.badRequest().body(e.getMessage());
			}
		};
	}

	@Bean("productSave")
	public Function<Product, Product> productSave() {
		return product -> restTemplate.postForEntity("http://localhost:8080/products/", product, Product.class)
				.getBody();
	}

	@Bean("productListGet")
	public Function<String, List<Product>> productListGet() {
		return id -> restTemplate.getForEntity("http://localhost:8080/products/", List.class).getBody();
	}

	@Bean("productDelete")
	public Function<String, String> productDelete() {
		return id -> {
			try {
				restTemplate.delete("http://localhost:8080/products/" + id);
			} catch (HttpClientErrorException.NotFound exception) {
				return "Product with id: " + id + " not found";
			}
			return "Deleted product with id: " + id + "successfully";
		};
	}

	@Bean("productPut")
	public Function<Product, Object> productPut() {
		return product -> {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<Product> entity = new HttpEntity<>(product, headers);
			try {				
				return restTemplate.exchange("http://localhost:8080/products", HttpMethod.PUT, entity, Product.class)
						.getBody();
			}catch (Exception e) {
				return ResponseEntity.badRequest().body(e.getMessage());
			}

		};
	}

}
