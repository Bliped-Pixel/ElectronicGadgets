package com.dao;

import java.sql.SQLException;
import java.util.List;

import com.dto.CustomerOrdersDto;
import com.model.Customers;

public interface CustomerDao {
	
	List <CustomerOrdersDto> CalculateTotalOrders() throws ClassNotFoundException, SQLException;
	List <Customers> GetCustomerDetails() throws ClassNotFoundException, SQLException;
	void UpdateCustomerInfo(Customers c) throws ClassNotFoundException, SQLException;
	boolean idExsist(int id) throws ClassNotFoundException, SQLException;
	Customers fetchCustomerById(int id) throws ClassNotFoundException, SQLException ;
}
