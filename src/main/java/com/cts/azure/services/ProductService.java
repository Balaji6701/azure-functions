package com.cts.azure.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.cts.azure.exceptions.ProductNotFoundException;
import com.cts.azure.models.Product;

@Service
public class ProductService {

	private static Map<String, Product> products = new HashMap<>();
	private static int count = 1001;
	static {
		products.put(count + "", new Product(count++ + "", "soap", 100.00));
		products.put(count + "", new Product(count++ + "", "Food", 200.00));
		products.put(count + "", new Product(count++ + "", "Oil", 200.00));
		products.put(count + "", new Product(count++ + "", "Dress", 800.00));
	}

	public ProductService() {

	}

	public List<Product> getAllProducts() {
		return new ArrayList<>(products.values());
	}

	public Product getProductById(String id) throws ProductNotFoundException {
		if (products.containsKey(id)) {
			return products.get(id);
		} else {
			throw new ProductNotFoundException("Product with id: " + id + " not found");
		}
	}

	public Product saveProduct(Product product) {
		product.setId(count++ + "");
		return products.put(product.getId(), product);
	}

}
