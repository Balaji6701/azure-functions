package com.balaji.cosmos.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Product with given id not found", value = HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends Exception {

	private static final long serialVersionUID = -1052917814384714274L;

	public ProductNotFoundException(String message) {
		super(message);
	}
}
