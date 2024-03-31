package com.model;

public class Products {
	
	
	private int id;
	private String productName;
	private String description;
	private double price;
	
	
	public Products() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Products(int id, String productName, String description, double price) {
		super();
		this.id = id;
		this.productName = productName;
		this.description = description;
		this.price = price;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}


	@Override
	public String toString() {
		return "Products [id=" + id + ", productName=" + productName + ", description=" + description + ", price="
				+ price + "]";
	}
	
	
	
	
}
