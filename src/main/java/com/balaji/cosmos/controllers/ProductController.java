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

/**
 * <h1>Product controller</h1> Product REST service to perform read, update
 * delete and create products.
 */
@RestController
@RequestMapping("products")
@Slf4j
@AllArgsConstructor
public class ProductController {

	private ProductService productService;

	/**
	 * retrieves product by id
	 * 
	 * @param productId Id of the product to be retrieved
	 * @return Product This returns product that matches the given id
	 */
	@GetMapping("/{productId}")
	public Product getProductById(@PathVariable("productId") String productId) throws ProductNotFoundException {
		log.info("Fetching product with id: {}", productId);
		return productService.getProductById(productId);
	}

	/**
	 * retrieves list of all products
	 * 
	 * @return List<Product> returns list of all products.
	 */
	@GetMapping
	public List<Product> getProducts() {
		log.info("Fetching all products");
		return productService.getProducts();
	}

	/**
	 * Saves product to database
	 * 
	 * @param product Product that is passed as request body
	 * @return Product This returns saved product
	 */
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED, value = HttpStatus.CREATED)
	public Product saveProduct(@RequestBody Product product) {
		log.info("Saving product: {}", product);
		return productService.saveProduct(product);
	}

	/**
	 * deletes product by id
	 * 
	 * @param productId Id of the product to be deleted
	 * @throws ProductNotFoundException throws, if no product with given id found
	 * @return ResponseEntity<String> return successfull message if deleted
	 */
	@DeleteMapping("/{productId}")
	public ResponseEntity<String> deleteProductById(@PathVariable("productId") String productId)
			throws ProductNotFoundException {
		log.info("Deleting product with id: {}", productId);
		productService.deleteProduct(productId);
		return ResponseEntity.ok("Product with id: " + productId + " deleted successfully");
	}

	/**
	 * updates product
	 * 
	 * @param product Product that is passed as request body
	 * @return Product returns the updated product
	 */
	@PutMapping
	public Product updateProduct(@RequestBody Product product) throws ProductNotFoundException {
		log.info("Updating product: {}", product);
		return productService.updateProduct(product);
	}
}
