package com.service;

import java.sql.SQLException;

import com.dao.OrderDetailsDao;
import com.dao.OrderDetailsDaoImpl;
import com.dto.OrdersOrderDetails;
import com.exception.OrderDetailsIdDoesNotExists;

public class OrderDetailsService {
	
	public double calculateSubtotal(int id)throws ClassNotFoundException, SQLException{
		OrderDetailsDao dao = new OrderDetailsDaoImpl();
		return dao.calculateSubtotal(id);
		
	}
	public OrdersOrderDetails getOrderDetailInfo(int id)throws ClassNotFoundException, SQLException{
		OrderDetailsDao dao = new OrderDetailsDaoImpl();
		return dao.getOrderDetailInfo(id);
	}
	public boolean updateQuantity(int id, int quantity)throws ClassNotFoundException, SQLException{
		OrderDetailsDao dao = new OrderDetailsDaoImpl();
		return dao.updateQuantity(id,quantity);
	}
	public double addDiscount(int id)throws ClassNotFoundException, SQLException{
		OrderDetailsDao dao = new OrderDetailsDaoImpl();
		return dao.addDiscount(id);
	}
	public boolean orderDetailsIdExists(int id)throws ClassNotFoundException, SQLException, OrderDetailsIdDoesNotExists{
		OrderDetailsDao dao = new OrderDetailsDaoImpl();
		return dao.orderDetailsIdExists(id);
	}

}
