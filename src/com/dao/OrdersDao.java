package com.dao;

import java.sql.SQLException;
import java.util.List;

import com.dto.OrdersOrderDetails;
import com.enums.Status;
import com.exception.OrderIdDoesNotExists;

public interface OrdersDao {
	
	public double calculateTotalAmount(int id) throws ClassNotFoundException, SQLException;
	public List<OrdersOrderDetails> getOrderDetails(int id)throws ClassNotFoundException, SQLException;
	public boolean updateOrderStatus(int id,Status status)throws ClassNotFoundException, SQLException;
	public boolean cancelOrder(int id)throws ClassNotFoundException, SQLException;
	public boolean ordersIdexists(int id)throws ClassNotFoundException, SQLException, OrderIdDoesNotExists;
	

}

