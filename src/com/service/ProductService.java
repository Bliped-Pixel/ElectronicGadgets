package com.service;

import java.sql.SQLException;

import com.model.Products;
import com.dao.ProductsDao;
import com.dao.ProductsDaoImpl;
import com.exception.ProductIdDoesNotExists;
import com.exception.ProductNameDoesNotExists;

public class ProductService {

	public Products GetProductDetail(int id) throws ClassNotFoundException, SQLException{
		
		ProductsDao dao = new ProductsDaoImpl();
		return dao.getProductDetail(id);
		
	}
	public Products GetProductDetail(String name) throws ClassNotFoundException, SQLException{
		
		ProductsDao dao = new ProductsDaoImpl();
		return dao.getProductDetail(name);
	}
	public boolean ProductIdExsist(int id)throws ClassNotFoundException, SQLException,ProductIdDoesNotExists{
		
		ProductsDao dao = new ProductsDaoImpl();
		return dao.productIdExists(id);
	}
	public boolean ProductNameExsist(String name)throws ClassNotFoundException, SQLException,ProductNameDoesNotExists{
		
		ProductsDao dao = new ProductsDaoImpl();
		return dao.productNameExists(name);
	}
	public boolean updateProductInfo(Products product)throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
		ProductsDao dao = new ProductsDaoImpl();
		return dao.updateProductInfo(product);
	}
	public boolean isProductInStock(int id)throws ClassNotFoundException, SQLException{
		ProductsDao dao = new ProductsDaoImpl();
		return dao.isProductInStock(id);
	}
	
	
}
