package com.example.services;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.exceptions.ProductNotFoundException;
import com.example.models.Product;
import com.example.repositories.ProductRepository;

@Service
@Transactional
public class ProductService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ProductRepository productRepository;

	public ProductService() {

	}

	public List<Product> getAllProducts() {
		logger.info("Retreiving all products");
		return productRepository.findAll();
	}

	public Product getProductById(String id) throws ProductNotFoundException {
		Optional<Product> product = productRepository.findById(id);
		if (product.isPresent()) {
			logger.info("Product with ID: {} found", id);
			return product.get();
		} else {
			logger.error("Product with ID: {} not found", id);
			throw new ProductNotFoundException("Product with id: " + id + " not found");
		}
	}

	public Product saveProduct(Product product) {
		logger.info("Saving product", product);
		return productRepository.save(product);
	}

}
