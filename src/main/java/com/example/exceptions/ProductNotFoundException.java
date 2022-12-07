package com.example.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NO_CONTENT, reason = "Product not found", value = HttpStatus.NO_CONTENT)
public class ProductNotFoundException extends Exception {

	private static final long serialVersionUID = -983340519381654878L;
	public ProductNotFoundException(String message) {
		super(message);
	}
}
