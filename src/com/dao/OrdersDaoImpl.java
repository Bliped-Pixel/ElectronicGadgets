package com.dao;

import com.dto.OrdersOrderDetails;
import com.util.DBConnection;
import com.enums.Status;
import com.exception.OrderIdDoesNotExists;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class OrdersDaoImpl implements OrdersDao{

	@Override
	public double calculateTotalAmount(int id) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Connection conn = DBConnection.getDBConn();
		String sql = "select sum(p.price*od.quantity) as total_amount"
				+ " from order_details od join orders o on o.id = od.orders_id join products p on p.id = od.products_id "
				+ "where od.orders_id = ? ";
		double ans = 0;
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, id);
		ResultSet result = pstmt.executeQuery(); 
		if(result.next())ans = result.getDouble("total_amount");
		DBConnection.dbclose();
		return ans;
	}

	@Override
	public List<OrdersOrderDetails> getOrderDetails(int id) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Connection conn = DBConnection.getDBConn();
		String sql = "select o.id, c.first_name, c.last_name,o.order_date, p.product_name,p.price, od.quantity from order_details od join orders o on o.id = od.orders_id"
				+" join customers c on o.customers_id = c.id join products p on p.id = od.products_id where o.id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, id);
		ResultSet result = pstmt.executeQuery();
		List <OrdersOrderDetails> list = new ArrayList<>();
		while(result.next()) {
			OrdersOrderDetails ood = new OrdersOrderDetails();
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
			
			list.add(ood);
		}
		DBConnection.dbclose();
		return list;
	}
	@Override
	public boolean updateOrderStatus(int id,Status status) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Connection conn = DBConnection.getDBConn();
		String sql = "update order_details set status = ? where orders_id = ?";
		boolean ans = false;
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, status.toString());
		pstmt.setInt(2, id);
		ans =  pstmt.execute();
		DBConnection.dbclose();
		return ans;
	}

	@Override
	public boolean cancelOrder(int id) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Connection conn = DBConnection.getDBConn();
		String query1 = "delete from order_details where orders_id = ?";
		String query2 = "delete from orders where id = ?";
		boolean ans = false;
		PreparedStatement pstmt1 = conn.prepareStatement(query1);
		pstmt1.setInt(1, id);
		PreparedStatement pstmt2;
		pstmt1.execute();
		pstmt2 = conn.prepareStatement(query2);
		pstmt2.setInt(1, id);
		ans = pstmt2.execute();
		DBConnection.dbclose();
		return ans;
	}

	@Override
	public boolean ordersIdexists(int id) throws ClassNotFoundException, SQLException, OrderIdDoesNotExists {
		// TODO Auto-generated method stub
		Connection conn = DBConnection.getDBConn();
		String sql = "select exists(select * from orders where id = ?) as valueExsist";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, id);
		ResultSet result = pstmt.executeQuery();
		Boolean ans = false;
		if(result.next())ans = result.getBoolean("valueExsist");
		if(!ans)throw new OrderIdDoesNotExists("Invalid Order ID Enter valid Order ID");
		DBConnection.dbclose();
		return ans;
	
	}


}
