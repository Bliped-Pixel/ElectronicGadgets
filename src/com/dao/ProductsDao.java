package com.dao;

import java.sql.SQLException;

import com.exception.ProductIdDoesNotExists;
import com.exception.ProductNameDoesNotExists;
import com.model.Products;

public interface ProductsDao {
	
	Products getProductDetail(int id) throws ClassNotFoundException, SQLException;
	Products getProductDetail(String name) throws ClassNotFoundException, SQLException;
	boolean updateProductInfo(Products p)throws ClassNotFoundException,SQLException;
	boolean productIdExists(int id)throws ClassNotFoundException, SQLException, ProductIdDoesNotExists;
	boolean productNameExists(String name)throws ClassNotFoundException, SQLException, ProductNameDoesNotExists;
	boolean isProductInStock(int id) throws ClassNotFoundException, SQLException;
}
