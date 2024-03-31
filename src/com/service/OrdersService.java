package com.service;

import com.dao.OrdersDao;
import com.dto.OrdersOrderDetails;
import com.dao.OrdersDaoImpl;
import com.enums.Status;
import com.exception.OrderIdDoesNotExists;

import java.util.List;
import java.sql.SQLException;

public class OrdersService {
	
	public double calculateTotalAmount(int id) throws ClassNotFoundException, SQLException {
		
		OrdersDao dao = new OrdersDaoImpl();
		return dao.calculateTotalAmount(id);

	}
	public List<OrdersOrderDetails> getOrderDetails(int id) throws ClassNotFoundException, SQLException {
		
		OrdersDao dao = new OrdersDaoImpl();
		return dao.getOrderDetails(id);
		
	}
	public boolean updateOrderStatus(int id, Status status)throws ClassNotFoundException, SQLException {
		OrdersDao dao = new OrdersDaoImpl();
		return dao.updateOrderStatus(id, status);
	}
	public boolean cancelOrder(int id)throws ClassNotFoundException, SQLException {
		OrdersDao dao = new OrdersDaoImpl();
		return dao.cancelOrder(id);
	}
	public boolean ordersIdexists(int id) throws ClassNotFoundException, SQLException,OrderIdDoesNotExists{
		// TODO Auto-generated method stub
		OrdersDao dao = new OrdersDaoImpl();
		return dao.ordersIdexists(id);
	}

}
