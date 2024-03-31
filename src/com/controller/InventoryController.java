package com.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.exception.InventoryIdDoesNotExists;
import com.exception.NegativeInventory;
import com.model.Products;
import com.service.InventoryService;

public class InventoryController {
	
	public static void main (String args[]) 
	{
		Scanner sc = new Scanner(System.in);
		InventoryService inventoryService = new InventoryService();
		while(true)
		{
			System.out.println("Choose An Operation");
			System.out.println("Press 1. To Get Product in Inventory.");
			System.out.println("Press 2. To Get Quantity of the Product in stock.");
			System.out.println("Press 3. To Add Stock to Inventory");
			System.out.println("Press 4. To Remove Stock from Inevntory ");
			System.out.println("Press 5. To Update Stock in Inventory");
			System.out.println("Press 6. To Check if Product exists");
			System.out.println("Press 7. To Get the net-worth of the Inventory.");
			System.out.println("Press 8. To list the products below the provided threshold quanity");
			System.out.println("Press 9. To list the products which are out of stock.");
			int choice = sc.nextInt();
			if(choice == 0)break;
			switch (choice) {
			
			case 1:
				try {
					System.out.println("Enter the Inventory Id of the Product you want to fetch : ");
					int id = sc.nextInt();
					if(!inventoryService.inventoryIdexists(id))break;
					
					System.out.println(inventoryService.getProduct(id).toString());
					break;
				}catch(ClassNotFoundException | SQLException | InventoryIdDoesNotExists e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
			case 2:
				try {
					
					System.out.println("Enter the Inventory Id of the Product you want to get the stock of : ");
					int id = sc.nextInt();
					if(!inventoryService.inventoryIdexists(id))break;
					
					System.out.println("For the Inventory Id : "+id+" the current stock equals : "+inventoryService.getQunatityInStock(id));
					break;
				}catch(ClassNotFoundException | SQLException | InventoryIdDoesNotExists e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
			case 3:
				try {
					System.out.println("Enter the Inventory Id of the Product you want to add stock to ");
					int id = sc.nextInt();
					if(!inventoryService.inventoryIdexists(id))break;
					
					System.out.println("Enter how much more quantity you want to add to the existing stock");
					int quantity = sc.nextInt();
					if(inventoryService.addToInventory(id, quantity))System.out.println("Succefully added to the system");					
					break;
				}catch(ClassNotFoundException | SQLException | InventoryIdDoesNotExists e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
			case 4:
				try {
					System.out.println("Enter the Inventory Id of the Product you want to add stock to ");
					int id = sc.nextInt();
					if(!inventoryService.inventoryIdexists(id))break;
					
					System.out.println("Enter how much quantity you want to remove from the existing stock");
					int quantity = sc.nextInt();
					if(inventoryService.removeFromInventory(id, quantity))System.out.println("Succefully removed from the system");
					break;
				}catch(ClassNotFoundException | SQLException | InventoryIdDoesNotExists | NegativeInventory e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
			case 5:
				try {
					System.out.println("Enter the Inventory Id of the Product you want to add stock to ");
					int id = sc.nextInt();
					if(!inventoryService.inventoryIdexists(id))break;
					
					System.out.println("Enter the update stock quantity: ");
					int quantity = sc.nextInt();
					if(inventoryService.updateStockQuanity(id, quantity))System.out.println("Succefully updated the stocks");
					break;
				}catch(ClassNotFoundException | SQLException | InventoryIdDoesNotExists | NegativeInventory e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
			case 6:
				try {
					System.out.println("Enter the Inventory Id to check if exists ");
					int id = sc.nextInt();
					if(!inventoryService.inventoryIdexists(id))break;
					else System.out.println("Then provided inventory exists!");
					break;
				}catch(ClassNotFoundException | SQLException | InventoryIdDoesNotExists e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
			case 7:
				try {
					
					System.out.println("The Entire current Net-worth of the inventory is : "+inventoryService.getInventoryValue());
					
					break;
				}catch(ClassNotFoundException | SQLException e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
			case 8:
				try {
					
					System.out.println("Enter the threshold to retrive the products below the provided stock threshold : ");
					int threshold = sc.nextInt();
					
					List<Products> list = inventoryService.listLowStockProducts(threshold);
					for(Products product : list)System.out.println(product.toString());
					
					break;
				}catch(ClassNotFoundException| SQLException e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
			case 9:
				try {
					
					List<Products> list = inventoryService.listOutOfStockProducts();
					for(Products product : list)System.out.println(product.toString());
				
					break;
				}catch(ClassNotFoundException| SQLException e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
			default:
				System.out.println("Inavlid Operation !");
				break;
			}
		}
		sc.close();
	}
	

}
