package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.exception.InventoryIdDoesNotExists;
import com.exception.NegativeInventory;
import com.exception.ProductIdDoesNotExists;
import com.model.Products;
import com.util.DBConnection;
import com.service.InventoryService;

public class InventoryDaoImpl implements InventoryDao {

	@Override
	public Products getProduct(int id) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Connection conn = DBConnection.getDBConn();
		String sql = "select p.id, p.product_name, p.description, p.price from products p join inventory i on i.products_id = p.id where i.id = ? ";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, id);
		ResultSet result = pstmt.executeQuery();
		Products product  = new Products();
		if(result.next()) {
			
			int productId = result.getInt("id");
			String productName = result.getString("product_name");
			String description = result.getString("description");
			double price = result.getDouble("price");
			
			product.setId(productId);
			product.setProductName(productName);
			product.setDescription(description);
			product.setPrice(price);
		
		}
		DBConnection.dbclose();
		return product;
	}

	@Override
	public int getQunatityInStock(int id) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Connection conn = DBConnection.getDBConn();
		String sql = "select quantity_in_stock from inventory where id = ? ";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, id);
		ResultSet result = pstmt.executeQuery();
		int ans = 0;
		if(result.next()) ans = result.getInt("quantity_in_stock");
		DBConnection.dbclose();
		return ans;
	}

	@Override
	public boolean addToInventory(int id,int quantity) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Connection conn = DBConnection.getDBConn();
		InventoryService is = new InventoryService();
		int updatedquantity = is.getQunatityInStock(id)+quantity;
		String sql = "update inventory set quantity_in_stock = ? where id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, updatedquantity);
		pstmt.setInt(2, id);
		boolean ans = pstmt.execute();
		DBConnection.dbclose();
		return ans;
	}

	@Override
	public boolean removeFromInventory(int id, int quantity) throws ClassNotFoundException, SQLException,NegativeInventory {
		// TODO Auto-generated method stub
		Connection conn = DBConnection.getDBConn();
		InventoryService is = new InventoryService();
		int updatedquantity = is.getQunatityInStock(id)-quantity;
		if(updatedquantity<0)throw new NegativeInventory("You cannot remove inventory more than it already has.");
		String sql = "update inventory set quantity_in_stock = ? where id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, updatedquantity);
		pstmt.setInt(2, id);
		boolean ans = pstmt.execute();
		DBConnection.dbclose();
		return ans;
	}

	@Override
	public boolean updateStockQuanity(int id, int newQuantity) throws ClassNotFoundException, SQLException, NegativeInventory {
		// TODO Auto-generated method stub
		Connection conn = DBConnection.getDBConn();
		if(newQuantity<0)throw new NegativeInventory("You cannot update inventory in negative values.");
		String sql = "update inventory set quantity_in_stock = ? where id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, newQuantity);
		pstmt.setInt(2, id);
		boolean ans = pstmt.execute();
		DBConnection.dbclose();
		return ans;
	}

	@Override
	public boolean isProductAvailable(int id) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Connection conn = DBConnection.getDBConn();
		String sql = "select quantity_in_stock from inventory where product_id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, id);
		ResultSet result = pstmt.executeQuery();
		if(result.next()) {
			int quantity = result.getInt("quantity_in_stock");
			if(quantity >0)return true;
		}
		return false;
	}

	@Override
	public double getInventoryValue() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Connection conn = DBConnection.getDBConn();
		String sql = "select sum(i.quantity_in_stock*p.price) as Inventory_net_worth from inventory i join products p on p.id = i.products_id";
		Statement stmt = conn.createStatement();
		ResultSet result = stmt.executeQuery(sql);
		double ans = 0;
		if(result.next())ans = result.getDouble("Inventory_net_worth");
		return ans;
	}

	@Override
	public List<Products> listLowStockProducts(int threshold) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Connection conn = DBConnection.getDBConn();
		String sql = "select p.id,p.product_name from products p join where inventory i on p.id = i.products_id where i.quantity_in_stock <= ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, threshold);
		ResultSet result = pstmt.executeQuery();
		List <Products> list = new ArrayList<>();
		while(result.next()) {
			Products product = new Products();
			int productId = result.getInt("id");
			String productName = result.getString("product_name");
			
			product.setId(productId);
			product.setProductName(productName);
			
			list.add(product);
		}
		if(list.equals(null))System.out.println("No Item found below the provided threshold");
		return list;
	}

	@Override
	public List<Products> listOutOfStockProducts() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Connection conn = DBConnection.getDBConn();
		String sql = "select p.id,p.product_name from products p join where inventory i on p.id = i.products_id where i.quantity_in_stock <= 0";
		Statement stmt = conn.createStatement();
		ResultSet result = stmt.executeQuery(sql);
		List <Products> list = new ArrayList<>();
		while(result.next()) {
			Products product = new Products();
			int productId = result.getInt("id");
			String productName = result.getString("product_name");
			
			product.setId(productId);
			product.setProductName(productName);
			
			list.add(product);
		}
		if(list.equals(null))System.out.println("All Items are stocked");
		return list;
	}

	@Override
	public boolean inventoryIdexists(int id) throws ClassNotFoundException, SQLException, InventoryIdDoesNotExists {
		// TODO Auto-generated method stub
		Connection conn = DBConnection.getDBConn();
		String sql = "select exists(select * from inventory where id = ?) as valueExsist";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, id);
		ResultSet result = pstmt.executeQuery();
		Boolean ans = false;
		if(result.next())ans = result.getBoolean("valueExsist");
		if(!ans)throw new InventoryIdDoesNotExists("Invalid Inventory ID Enter valid Inventory ID");
		DBConnection.dbclose();
		return ans;
	
	}

	@Override
	public boolean productIdExsist(int id) throws ClassNotFoundException, SQLException, ProductIdDoesNotExists {
		// TODO Auto-generated method stub
		Connection conn = DBConnection.getDBConn();
		String sql = "select exists(select * from inventory where products_id = ?) as valueExsist";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, id);
		ResultSet result = pstmt.executeQuery();
		Boolean ans = false;
		if(result.next())ans = result.getBoolean("valueExsist");
		if(!ans)throw new ProductIdDoesNotExists ("Product Id Not found in the Inventory");
		DBConnection.dbclose();
		return ans;
	}

}
