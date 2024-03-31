package com.exception;

public class ProductNameDoesNotExists extends Exception {

	private static final long serialVersionUID = 1L;
	private String message;
	
	public ProductNameDoesNotExists(String message)
	{
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
	
	

}
