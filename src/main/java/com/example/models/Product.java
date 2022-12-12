package com.example.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Product {

	private String productId;
	private String id;
	private String productName;
	private double price;
	private String productCategory;
	private boolean active;
	private String brand;

	public Product(String productId, String productName, double price, String productCategory, boolean active,
			String brand) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.price = price;
		this.productCategory = productCategory;
		this.active = active;
		this.brand = brand;
	}

}
