package com.dao;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

import com.util.DBConnection;
import com.dto.CustomerOrdersDto;
import com.model.Customers;

public class CustomerDaoImpl implements CustomerDao{

	
	@Override
	public List<CustomerOrdersDto> CalculateTotalOrders() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
		Connection conn = DBConnection.getDBConn();
		String sql = "select c.id,c.first_name,c.last_name,count(c.id) as total_orders from customers c join orders o on c.id = o.customers_id group by c.id";
		Statement stmt = conn.createStatement();
		ResultSet result= stmt.executeQuery(sql);
		List<CustomerOrdersDto> list = new ArrayList<>();
		while(result.next())
		{
			CustomerOrdersDto co = new CustomerOrdersDto();
			int id = result.getInt("id");
			String firstName = result.getString("first_name");
			String lastName = result.getString("last_name");
			String totalOrders = result.getString("total_orders");	
			co.setId(id);
			co.setFirst_name(firstName);
			co.setLast_name(lastName);
			co.setTotal_orders(totalOrders);
			list.add(co);
		}
		return list;
	}

	@Override
	public List<Customers> GetCustomerDetails() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Connection conn = DBConnection.getDBConn();
		String sql = "select * from customers";
		Statement stmt = conn.createStatement();
		ResultSet result = stmt.executeQuery(sql);
		List<Customers> list = new ArrayList<>();
		while(result.next())
		{
			Customers c = new Customers();
			int id = result.getInt("id");
			String firstName = result.getString("first_name");
			String lastName = result.getString("last_name");
			String email = result.getString("email");
			String phone = result.getString("phone");
			String address = result.getString("address");
			
			c.setId(id);
			c.setFirstName(firstName);
			c.setLastName(lastName);
			c.setEmail(email);
			c.setPhone(phone);
			c.setAddress(address);
			
			list.add(c);	
		}
		
		return list;
	}

	@Override
	public void UpdateCustomerInfo(Customers c) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
		Connection conn = DBConnection.getDBConn();
		String sql = "update customers set email = ?, phone = ?, address = ? where id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, c.getEmail());
		pstmt.setString(2, c.getPhone());
		pstmt.setString(3, c.getAddress());
		pstmt.setInt(4, c.getId());
		pstmt.executeUpdate();
		System.out.println("Succesfully updated!");
		DBConnection.dbclose();
	}

	@Override
	public boolean idExsist(int id) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
			Connection conn = DBConnection.getDBConn();
			String sql = "select exists(select * from customers where id = "+id+") as valueExsist";
			Statement stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery(sql);
			Boolean ans = false;
			if(result.next())ans = result.getBoolean("valueExsist");
			DBConnection.dbclose();
			return ans;
		
		
	}

	@Override
	public Customers fetchCustomerById(int id)  throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Connection conn = DBConnection.getDBConn();
		String sql = "select * from customers where id = "+id+"";
		Statement stmt = conn.createStatement();
		ResultSet result = stmt.executeQuery(sql);
		Customers c = new Customers();
		if(result.next())
		{	
			String firstName = result.getString("first_name");
			String lastName = result.getString("last_name");
			String email = result.getString("email");
			String phone = result.getString("phone");
			String address = result.getString("address");
			
			c.setId(id);
			c.setFirstName(firstName);
			c.setLastName(lastName);
			c.setEmail(email);
			c.setPhone(phone);
			c.setAddress(address);
		}	
		DBConnection.dbclose();
		return c;
	}

}
