package com.balaji.cosmos.exceptions;

public class UserNotFoundException extends Exception{
	private static final long serialVersionUID = 5427694529735620352L;
	public UserNotFoundException(String message) {
		super(message);
	}
}
