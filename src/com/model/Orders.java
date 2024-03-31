package com.model;

import java.time.LocalDate;

public class Orders {
	
	private int id;
	private String customer;
	private LocalDate orderDate;
	private double totalAmount;
	
	public Orders(int id, String customer, LocalDate orderDate, double totalAmount) {
		super();
		this.id = id;
		this.customer = customer;
		this.orderDate = orderDate;
		this.totalAmount = totalAmount;
	}

	public Orders() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	
	

}
