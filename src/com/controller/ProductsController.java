package com.controller;

import java.sql.SQLException;
import java.util.Scanner;

import com.service.ProductService;
import com.exception.*;
import com.model.Products;
import com.service.InventoryService;


public class ProductsController {
	
	public static void main (String args[]) 
	{
		Scanner sc = new Scanner(System.in);
		ProductService productService = new ProductService();
		InventoryService inventoryService = new InventoryService();
		while(true)
		{
			System.out.println("Choose An Operation");
			System.out.println("Press 1. Retrive and Display detailed information about the product.");
			System.out.println("Press 2. Update Products.");
			System.out.println("Press 3. Check Prodcut Stock.");
			System.out.println("Press 0. To Terminate");
			int choice = sc.nextInt();
			if(choice == 0)break;
			switch (choice)
			{
			case 1:
				try {
					Products product = new Products();
					System.out.println("To display Product detail either enter Product ID or Product Name");
					System.out.println("Press 1. For Fetch by Product ID");
					System.out.println("Press 2. For Fetch by Product Name");
					int o = sc.nextInt();
					if(o==1) {
						System.out.println("Enter Product ID:");
						int id = sc.nextInt();
						if(productService.ProductIdExsist(id))break;
						product = productService.GetProductDetail(id);
					}
					else if(o==2) {
						System.out.println("Enter Product ID:");
						String name = sc.nextLine();
						if(productService.ProductNameExsist(name))break;
						product = productService.GetProductDetail(name);
					}
					else {
						System.out.println("Invalid Option");
						break;
					}
					System.out.println(product.toString());
					
				}catch(ClassNotFoundException | SQLException | ProductNameDoesNotExists |ProductIdDoesNotExists e)
				{
					System.out.println(e.getMessage());
				}
			case 2:
				try {
					
					
					System.out.println("Enter the Product ID you want to update ");
					int id = sc.nextInt();
					if(productService.ProductIdExsist(id))break;
					Products product = productService.GetProductDetail(id);
					
					System.out.println("Enter the Updated Name for the Product if not press 0: ");
					String productName = sc.nextLine();
					if(!productName.equals("0"))product.setProductName(productName);
					
					System.out.println("Enter the Updated Description for the Product if not press 0: ");
					String description = sc.nextLine();
					if(!description.equals("0"))product.setDescription(description);
					
					System.out.println("Enter the Updated Price for the product if not press 0: ");
					double price = sc.nextDouble();
					if(price != 0)product.setPrice(price);
					
					productService.updateProductInfo(product);
					
					System.out.println("Sucessfully Updated Product.");
				}catch(ClassNotFoundException | SQLException | ProductIdDoesNotExists e) {
					System.out.println(e.getMessage());
				}
				
			case 3:
				try {
					System.out.println("Enter the Product ID to check the stock:  ");
					int id = sc.nextInt();
					if(inventoryService.productIdExsist(id))break;
					if(productService.isProductInStock(id))System.out.println("The Product Id : "+id+"is currently in stock");
					else System.out.println("Product out of stock !!");
				}catch(Exception e) {
					
					
				}
			default:
				System.out.println("Invalid Operation !");
				break;
			}
			
		}
		sc.close();
	}

}
