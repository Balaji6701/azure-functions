package com.balaji.cosmos.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.balaji.cosmos.entities.Product;
import com.balaji.cosmos.exceptions.ProductNotFoundException;
import com.balaji.cosmos.services.ProductService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("products")
@Slf4j
@AllArgsConstructor
public class ProductController {
	
	private ProductService productService;
	
	@GetMapping("/{productId}")
	public Product getProductById(@PathVariable("productId")String productId) throws ProductNotFoundException {
		log.info("Fetching product with id: {}",productId);
		return productService.getProductById(productId);
	}
	
	@GetMapping
	public List<Product> getProducts() {
		log.info("Fetching all products");
		return productService.getProducts();
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED, value = HttpStatus.CREATED)
	public Product saveProduct(@RequestBody Product product) {
		log.info("Saving product: {}", product);
		return productService.saveProduct(product);
	}
	
	@DeleteMapping("/{productId}")
	public ResponseEntity<String> deleteProductById(@PathVariable("productId")String productId) throws ProductNotFoundException {
		log.info("Deleting product with id: {}",productId);
		productService.deleteProduct(productId);
		return ResponseEntity.ok("Product with id: " + productId + " deleted successfully");
	}
	
	@PutMapping
	public Product updateProduct(@RequestBody Product product) throws ProductNotFoundException {
		log.info("Updating product: {}", product);
		return productService.updateProduct(product);
	}
}
