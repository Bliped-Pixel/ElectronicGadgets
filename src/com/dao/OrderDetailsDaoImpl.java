package com.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.dto.OrdersOrderDetails;
import com.exception.OrderDetailsIdDoesNotExists;
import com.exception.OrderIdDoesNotExists;
import com.util.DBConnection;

public class OrderDetailsDaoImpl implements OrderDetailsDao {

	@Override
	public double calculateSubtotal(int id) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Connection conn = DBConnection.getDBConn();
		String sql = "select sum(p.price*od.quantity) as total_amount"
				+ " from order_details od join orders o on o.id = od.orders_id join products p on p.id = od.products_id "
				+ "where od.id = ? ";
		double ans = 0;
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, id);
		ResultSet result = pstmt.executeQuery(); 
		if(result.next())ans = result.getDouble("total_amount");
		DBConnection.dbclose();
		return ans;
	}

	@Override
	public OrdersOrderDetails getOrderDetailInfo(int id) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub]
		Connection conn = DBConnection.getDBConn();
		String sql = "select o.id, c.first_name, c.last_name,o.order_date, p.product_name,p.price, od.quantity from order_details od join orders o on o.id = od.orders_id"
				+" join customers c on o.customers_id = c.id join products p on p.id = od.products_id where od.id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, id);
		ResultSet result = pstmt.executeQuery();
		OrdersOrderDetails ood = new OrdersOrderDetails();
		if(result.next()) {
			
			int ordersId = result.getInt("id");
			String firstName = result.getString("first_name");
			String lastName = result.getString("last_name");
			Date orderDate = result.getDate("order_date");
			String productName = result.getString("product_name");
			double price = result.getDouble("price");
			int quantity = result.getInt("quantity");
			
			ood.setOrdersId(ordersId);
			ood.setFirstName(firstName);
			ood.setLastName(lastName);
			ood.setOrderDate(orderDate.toLocalDate());
			ood.setProductName(productName);
			ood.setPrice(price);
			ood.setQuantity(quantity);
		}
		DBConnection.dbclose();
		return ood;
	}

	@Override
	public boolean updateQuantity(int id, int quantity) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Connection conn = DBConnection.getDBConn();
		String sql = "update order_details set quantity = ? where id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, quantity);
		pstmt.setInt(2, id);
		boolean ans = pstmt.execute();
		DBConnection.dbclose();
		return ans;
	}

	@Override
	public double addDiscount(int id) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		// There is no table in for Discount in the orderDetials table
		return 0;
	}
	
	public boolean orderDetailsIdExists(int id) throws ClassNotFoundException, SQLException, OrderDetailsIdDoesNotExists {
		// TODO Auto-generated method stub
		Connection conn = DBConnection.getDBConn();
		String sql = "select exists(select * from order_details where id = ?) as valueExsist";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, id);
		ResultSet result = pstmt.executeQuery();
		Boolean ans = false;
		if(result.next())ans = result.getBoolean("valueExsist");
		if(!ans)throw new OrderDetailsIdDoesNotExists("Invalid Order Details ID Enter valid Order Details ID");
		DBConnection.dbclose();
		return ans;
	
	}

}
