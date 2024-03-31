package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import com.exception.ProductIdDoesNotExists;
import com.exception.ProductNameDoesNotExists;
import com.model.Products;
import com.util.DBConnection;

public class ProductsDaoImpl implements ProductsDao {

	@Override
	public Products getProductDetail(int id) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Connection conn = DBConnection.getDBConn();
		String queryId = "select * from products where id = ?";
		PreparedStatement pstmt = conn.prepareStatement(queryId);
		pstmt.setInt(1, id);
		ResultSet result = pstmt.executeQuery(queryId);
		Products product = new Products();
		if(result.next())
		{
			String productName = result.getString("product_name");
			String description = result.getString("description");
			double price = result.getDouble("Price");
			
			product.setId(id);
			product.setProductName(productName);
			product.setDescription(description);
			product.setPrice(price);
		}
		return product;
	}

	@Override
	public Products getProductDetail(String name) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Connection conn = DBConnection.getDBConn();
		String queryName = "select * from products where product_name = ?";
		PreparedStatement pstmt = conn.prepareStatement(queryName);
		pstmt.setString(1, name);
		ResultSet result = pstmt.executeQuery(queryName);
		Products product = new Products();
		if(result.next())
		{
			int id = result.getInt("id");
			String description = result.getString("description");
			double price = result.getDouble("Price");
			
			product.setId(id);
			product.setProductName(name);
			product.setDescription(description);
			product.setPrice(price);
		}
		return product;
	}

	@Override
	public boolean productIdExists(int id) throws ClassNotFoundException, SQLException, ProductIdDoesNotExists {
		// TODO Auto-generated method stub
		Connection conn = DBConnection.getDBConn();
		String sql = "select exists(select * from products where id = ?) as value_exists";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, id);
		ResultSet result = pstmt.executeQuery(sql);
		boolean ans = false;
		if(result.next())ans = result.getBoolean("value_exists");
		if(!ans)throw new ProductIdDoesNotExists("Invalid Product ID Enter valid Product ID");
		return ans;
	}

	@Override
	public boolean productNameExists(String name) throws ClassNotFoundException, SQLException,ProductNameDoesNotExists{
		// TODO Auto-generated method stub
		Connection conn = DBConnection.getDBConn();
		String sql = "select exists(select * from products where product_name = ?) as value_exists";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, name);
		ResultSet result = pstmt.executeQuery(sql);
		boolean ans = false;
		if(result.next())ans = result.getBoolean("value_exists");
		if(!ans)throw new ProductNameDoesNotExists("Invalid Product Name Enter valid Product Name");
		return ans;
	}

	@Override
	public boolean updateProductInfo(Products p) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Connection conn = DBConnection.getDBConn();
		String sql = "update products set product_name = ?, description = ?, price = ? where id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, p.getProductName());
		pstmt.setString(2, p.getDescription());
		pstmt.setDouble(3, p.getPrice());
		pstmt.setInt(4, p.getId());
		return pstmt.execute();
	}

	@Override
	public boolean isProductInStock(int id) throws ClassNotFoundException, SQLException{
		// TODO Auto-generated method stub
		Connection conn = DBConnection.getDBConn();
		String sql = "select quantity_in_stock from inventory where product_id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, id);
		ResultSet result = pstmt.executeQuery(sql);
		if(result.next()) {
			int quantity = result.getInt("quantity_in_stock");
			if(quantity >0)return true;
		}
		return false;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


}
