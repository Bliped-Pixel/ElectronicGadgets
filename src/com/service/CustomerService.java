package com.service;

import java.sql.SQLException;
import java.util.List; 

import com.dao.CustomerDao;
import com.dao.CustomerDaoImpl;
import com.dto.CustomerOrdersDto;
import com.model.Customers;

public class CustomerService {
	
	public List<CustomerOrdersDto> CalculateTotalOrders() throws ClassNotFoundException, SQLException
	{
		CustomerDao dao = new CustomerDaoImpl();
		return dao.CalculateTotalOrders();
		
	}
	public List <Customers> GetCustomerDetails() throws ClassNotFoundException, SQLException
	{
		CustomerDao dao = new CustomerDaoImpl();
		return dao.GetCustomerDetails();
	}
	public boolean idExsist(int id) throws ClassNotFoundException, SQLException
	{
		CustomerDao dao = new CustomerDaoImpl();
		return dao.idExsist(id);
	}
	public Customers fetchCustomerById(int id)  throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		CustomerDao dao = new CustomerDaoImpl();
		return dao.fetchCustomerById(id);
	}
	public void updateCustomerInfo(Customers c) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		CustomerDao dao = new CustomerDaoImpl();
		dao.UpdateCustomerInfo(c);
		return ;
		
	}

}