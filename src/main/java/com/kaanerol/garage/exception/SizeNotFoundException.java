package com.kaanerol.garage.exception;

public class SizeNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	
	public SizeNotFoundException(String message) {
		super(message);
	}
}
