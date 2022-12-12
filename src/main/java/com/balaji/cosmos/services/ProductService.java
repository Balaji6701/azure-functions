package com.balaji.cosmos.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.azure.cosmos.models.PartitionKey;
import com.balaji.cosmos.entities.Product;
import com.balaji.cosmos.exceptions.ProductNotFoundException;
import com.balaji.cosmos.repositories.ProductRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class ProductService {

	private ProductRepository productRepository;

	public List<Product> getProducts() {
		List<Product> products = new ArrayList<>();
		productRepository.findAll().forEach(products::add);
		return products;
	}

	public Product getProductById(String id) throws ProductNotFoundException {
		Optional<Product> optionalProduct = productRepository.findById(id);
		if (optionalProduct.isPresent()) {
			log.info("Found product with id: {}", id);
			return optionalProduct.get();
		} else {
			log.error("Product with id: {} not found", id);
			throw new ProductNotFoundException("Product with id: " + id + " not found");
		}
	}

	public Product saveProduct(Product product) {
		return productRepository.save(product);
	}

	public void deleteProduct(String id) throws ProductNotFoundException {
		Product product = this.getProductById(id);
		log.info("Found product with id: {}", id);
		productRepository.deleteById(id, new PartitionKey(product.getProductId()));
	}
	
	public Product updateProduct(Product product) throws ProductNotFoundException {
		this.getProductById(product.getId());
		return productRepository.save(product);
	}
}
