package com.controller;

import java.sql.SQLException;
import java.util.Scanner;

import com.exception.OrderDetailsIdDoesNotExists;
import com.service.OrderDetailsService;

public class OrderDetailsController {

	public static void main(String args[]) {
	OrderDetailsService orderDetailsService =  new OrderDetailsService();
	Scanner sc = new Scanner(System.in);
	
		while(true){
	
			System.out.println("Choose An Operation");
			System.out.println("Press 1. To Calculate the sub total amount of the order details");
			System.out.println("Press 2. To Fetch Order Details");
			System.out.println("Press 3. To Update quanity for the order");
			System.out.println("Press 4. To Add discount");
			System.out.println("Press 0. To Terminate");
			int choice = sc.nextInt();
			if(choice == 0)break;
			switch (choice) {
			case 1:
				try {
					System.out.println("Enter the Order Details id: ");
					int id = sc.nextInt();
					if(!orderDetailsService.orderDetailsIdExists(id))break;
					orderDetailsService.calculateSubtotal(id);
					System.out.println("The Total amount for order Id "+id+" = "+orderDetailsService.calculateSubtotal(id));
					break;
				}catch (ClassNotFoundException| SQLException|OrderDetailsIdDoesNotExists e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
			case 2:
				try {
					System.out.println("Enter the Order Details id to fetch the required details: ");
					int id = sc.nextInt();
					if(!orderDetailsService.orderDetailsIdExists(id))break;
					
					System.out.println(orderDetailsService.getOrderDetailInfo(id).toString());
					
					break;
				}catch (ClassNotFoundException| SQLException|OrderDetailsIdDoesNotExists e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
			case 3:
				try {
					System.out.println("Enter the Order Details id to update the quantity of the order: ");
					int id = sc.nextInt();
					if(!orderDetailsService.orderDetailsIdExists(id))break;
					
					System.out.println("Enter the quanity you want to update to:");
					int quantity = sc.nextInt();
					if(orderDetailsService.updateQuantity(id, quantity))System.out.println("Successfully updated quantity!");
					break;
				}catch (ClassNotFoundException| SQLException|OrderDetailsIdDoesNotExists e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
			case 4:
				try {
					System.out.println("Enter the Order Details id to update the quantity of the order: ");
					int id = sc.nextInt();
					if(!orderDetailsService.orderDetailsIdExists(id))break;
					
					orderDetailsService.addDiscount(id);
					//There is no table for Discount 
					break;
				}catch (ClassNotFoundException| SQLException|OrderDetailsIdDoesNotExists e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
			default:
				System.out.println("Invalid Operation");
				break;
			}
		}
	}
}
