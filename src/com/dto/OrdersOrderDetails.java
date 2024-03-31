package com.dto;

import java.time.LocalDate;



public class OrdersOrderDetails {

	private int ordersId;
	private String firstName;
	private String lastName;
	private LocalDate orderDate;
	private String productName;
	private double price;
	private int quantity;
	public OrdersOrderDetails(int ordersId, String firstName, String lastName, LocalDate orderDate,
			String productName, double price, int quantity) {
		super();
		this.ordersId = ordersId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.orderDate = orderDate;
		this.productName = productName;
		this.price = price;
		this.quantity = quantity;
	}
	public OrdersOrderDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getOrdersId() {
		return ordersId;
	}
	public void setOrdersId(int ordersId) {
		this.ordersId = ordersId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public LocalDate getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "CompleteOrderDetails [ordersId=" + ordersId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", orderDate=" + orderDate + ", productName=" + productName + ", price=" + price + ", quantity="
				+ quantity + "]";
	}
	
	
	
	
}
