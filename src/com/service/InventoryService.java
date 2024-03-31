package com.service;

import java.sql.SQLException;
import java.util.List;

import com.dao.InventoryDao;
import com.dao.InventoryDaoImpl;
import com.exception.InventoryIdDoesNotExists;
import com.exception.NegativeInventory;
import com.exception.ProductIdDoesNotExists;
import com.model.Products;

public class InventoryService {

	public Products getProduct(int id)throws ClassNotFoundException, SQLException{
		
		InventoryDao dao = new InventoryDaoImpl();
		return dao.getProduct(id);
		
	}
	public int getQunatityInStock(int id)throws ClassNotFoundException, SQLException{
		InventoryDao dao = new InventoryDaoImpl();
		return dao.getQunatityInStock(id);
		
	}
	public boolean addToInventory(int id, int quantity)throws ClassNotFoundException, SQLException{
		InventoryDao dao = new InventoryDaoImpl();
		return dao.addToInventory(id,quantity);
		
	}
	public boolean removeFromInventory(int id, int quantity)throws ClassNotFoundException, SQLException,NegativeInventory{
		InventoryDao dao = new InventoryDaoImpl();
		return dao.removeFromInventory(id, quantity);
		
	}
	public boolean updateStockQuanity(int id, int newQuantity)throws ClassNotFoundException, SQLException,NegativeInventory{
		InventoryDao dao = new InventoryDaoImpl();
		return dao.updateStockQuanity(id, newQuantity);
		
	}
	public boolean isProductAvailable(int id)throws ClassNotFoundException, SQLException{
		InventoryDao dao = new InventoryDaoImpl();
		return dao.isProductAvailable(id);
		
	}
	public double getInventoryValue()throws ClassNotFoundException, SQLException{
		InventoryDao dao = new InventoryDaoImpl();
		return dao.getInventoryValue();
	
	}
	public List<Products> listLowStockProducts(int threshold)throws ClassNotFoundException, SQLException{
		InventoryDao dao = new InventoryDaoImpl();
		return dao.listLowStockProducts(threshold);
		
	}
	public List<Products> listOutOfStockProducts()throws ClassNotFoundException, SQLException{
		InventoryDao dao = new InventoryDaoImpl();
		return dao.listOutOfStockProducts();
		
	}
	public boolean inventoryIdexists(int id) throws ClassNotFoundException, SQLException, InventoryIdDoesNotExists{
		InventoryDao dao = new InventoryDaoImpl();
		return dao.inventoryIdexists(id);
		
	}
	public boolean productIdExsist(int id) throws ClassNotFoundException, SQLException, ProductIdDoesNotExists{
		// TODO Auto-generated method stub
		InventoryDao dao = new InventoryDaoImpl();
		return dao.productIdExsist(id);
	}
	
}
