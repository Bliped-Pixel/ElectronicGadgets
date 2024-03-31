package com.dao;

import com.dto.OrdersOrderDetails;
import com.exception.OrderDetailsIdDoesNotExists;
import com.exception.OrderIdDoesNotExists;

import java.sql.SQLException;

public interface OrderDetailsDao {
	
	
	public double calculateSubtotal(int id)throws ClassNotFoundException, SQLException;
	public OrdersOrderDetails getOrderDetailInfo(int id)throws ClassNotFoundException, SQLException;
	public boolean updateQuantity(int id, int quantity)throws ClassNotFoundException, SQLException;
	public double addDiscount(int id)throws ClassNotFoundException, SQLException;
	public boolean orderDetailsIdExists(int id)throws ClassNotFoundException, SQLException, OrderDetailsIdDoesNotExists;
}
