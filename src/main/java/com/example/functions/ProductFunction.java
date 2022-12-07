package com.example.functions;

import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.models.Product;
import com.example.repositories.ProductRepository;

@Configuration
public class ProductFunction {

	@Autowired
	private ProductRepository productRepository;

	@Bean("productGet")
	public Function<String, Product> productGet() {
		return id -> productRepository.findById(id).get();
	}

	@Bean("productSave")
	public Function<Product, Product> productSave() {
		return product -> productRepository.save(product);
	}

	@Bean("productListGet")
	public Function<String, List<Product>> productListGet() {
		return id -> productRepository.findAll();
	}

	@Bean("productDelete")
	public Function<String, String> productDelete() {
		return id -> {
			productRepository.deleteById(id);
			return "Product deleted successfully";
		};
	}
	
	@Bean("productPut")
	public Function<Product, Product> productPut() {
		return product -> productRepository.save(product);
	}

}
