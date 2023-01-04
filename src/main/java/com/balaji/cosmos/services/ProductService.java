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


/**
 * <h1>Product service - Performs crud operation using repository</h1>
 *
 */
@Service
@AllArgsConstructor
@Slf4j
public class ProductService {
	
	// ProductRepository - Talks with cosmos database
	private ProductRepository productRepository;
	
	/**
	 * retrieves list of all products
	 * 
	 * @return List<Product> returns list of all products.
	 */
	public List<Product> getProducts() {
		List<Product> products = new ArrayList<>();
		productRepository.findAll().forEach(products::add);
		return products;
	}
	
	/**
	 * retrieves product by id
	 * 
	 * @param productId Id of the product to be retrieved
	 * @throws ProductNotFoundException throws, if no product with given id found
	 * @return Product This returns product that matches the given id
	 */
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
	
	/**
	 * Saves product to database
	 * 
	 * @param product Product that is passed as request body
	 * @return Product This returns saved product
	 */
	public Product saveProduct(Product product) {
		
		return productRepository.save(product);
	}
	
	/**
	 * deletes product by id
	 * 
	 * @param productId Id of the product to be deleted
	 * @throws ProductNotFoundException throws, if no product with given id found
	 * @return ResponseEntity<String> return successfull message if deleted
	 */
	public void deleteProduct(String id) throws ProductNotFoundException {
		Product product = this.getProductById(id);
		log.info("Found product with id: {}", id);
		productRepository.deleteById(id, new PartitionKey(product.getProductId()));
	}
	
	/**
	 * updates product
	 * 
	 * @param product Product that is passed as request body
	 * @return Product returns the updated product
	 */
	public Product updateProduct(Product product) throws ProductNotFoundException {
		this.getProductById(product.getId());
		return productRepository.save(product);
	}
}
