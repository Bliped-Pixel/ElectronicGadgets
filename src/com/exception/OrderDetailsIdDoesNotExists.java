package com.exception;

public class OrderDetailsIdDoesNotExists extends Exception{
	
private static final long serialVersionUID = 1L;
	
	private String message;
	
	public OrderDetailsIdDoesNotExists  (String message)
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
