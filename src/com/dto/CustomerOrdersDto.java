package com.dto;

public class CustomerOrdersDto {
	
	private int id;
	private String first_name;
	private String last_name;
	private String total_orders;
	
	public CustomerOrdersDto() {
		// TODO Auto-generated constructor stub
	}
	public CustomerOrdersDto(int id, String first_name, String last_name, String total_orders) {
		super();
		this.id = id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.total_orders = total_orders;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getTotal_orders() {
		return total_orders;
	}
	public void setTotal_orders(String total_orders) {
		this.total_orders = total_orders;
	}
	
	@Override
	public String toString() {
		return "CustomerOrdersDto [id=" + id + ", first_name=" + first_name + ", last_name=" + last_name
				+ ", total_orders=" + total_orders + "]";
	}

	

}
