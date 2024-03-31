package com.dao;

import com.model.Products;
import com.exception.InventoryIdDoesNotExists;
import com.exception.NegativeInventory;
import com.exception.ProductIdDoesNotExists;

import java.sql.SQLException;
import java.util.List;

public interface InventoryDao {
	
	public Products getProduct(int id)throws ClassNotFoundException, SQLException;
	public int getQunatityInStock(int id)throws ClassNotFoundException, SQLException;
	public boolean addToInventory(int id, int quantity)throws ClassNotFoundException, SQLException;
	public boolean removeFromInventory(int id, int quantity)throws ClassNotFoundException, SQLException,NegativeInventory;
	public boolean updateStockQuanity(int id, int newQuantity)throws ClassNotFoundException, SQLException, NegativeInventory;
	public boolean isProductAvailable(int id)throws ClassNotFoundException, SQLException;
	public double getInventoryValue()throws ClassNotFoundException, SQLException;
	public List<Products> listLowStockProducts(int threshold)throws ClassNotFoundException, SQLException;
	public List<Products> listOutOfStockProducts()throws ClassNotFoundException, SQLException;
	public boolean inventoryIdexists(int id) throws ClassNotFoundException, SQLException, InventoryIdDoesNotExists;
	public boolean productIdExsist(int id)throws ClassNotFoundException, SQLException, ProductIdDoesNotExists;
}
