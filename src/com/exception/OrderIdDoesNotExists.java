package com.exception;

public class OrderIdDoesNotExists extends Exception {
	
private static final long serialVersionUID = 1L;
	
	private String message;
	
	public OrderIdDoesNotExists (String message)
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
