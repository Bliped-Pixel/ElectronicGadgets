package com.model;

import java.time.LocalDate;

public class Inventory {
	
	private int id;
	private int product_id;
	private int QuantityInStock;
	private LocalDate localStockUpdate;
	public Inventory() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Inventory(int id, int product_id, int quantityInStock, LocalDate localStockUpdate) {
		super();
		this.id = id;
		this.product_id = product_id;
		this.QuantityInStock = quantityInStock;
		this.localStockUpdate = localStockUpdate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public int getQuantityInStock() {
		return QuantityInStock;
	}
	public void setQuantityInStock(int quantityInStock) {
		QuantityInStock = quantityInStock;
	}
	public LocalDate getLocalStockUpdate() {
		return localStockUpdate;
	}
	public void setLocalStockUpdate(LocalDate localStockUpdate) {
		this.localStockUpdate = localStockUpdate;
	}
	@Override
	public String toString() {
		return "Inventory [id=" + id + ", product_id=" + product_id + ", QuantityInStock=" + QuantityInStock
				+ ", localStockUpdate=" + localStockUpdate + "]";
	}
	
	
	

}
