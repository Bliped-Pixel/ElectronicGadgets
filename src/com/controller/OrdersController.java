package com.controller;

import com.dto.OrdersOrderDetails;
import com.enums.Status;
import com.exception.OrderIdDoesNotExists;
import com.service.OrdersService;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.List;

public class OrdersController {
	
	public static void main(String args[]) {
		
		OrdersService ordersService =  new OrdersService();
		Scanner sc = new Scanner(System.in);
		
		while(true)
		{
			System.out.println("Choose An Operation");
			System.out.println("Press 1. To Calculate the total amount of the order");
			System.out.println("Press 2. To Fetch Order Details");
			System.out.println("Press 3. To Update Order Status");
			System.out.println("Press 3. To Cancel Order");
			System.out.println("Press 0. To Terminate");
			int choice = sc.nextInt();
			if(choice == 0)break;
			switch (choice) {
			
			case 1:
				try {
					System.out.println("Enter the order ID you want to Calculte the total amount for that order: ");
					int id = sc.nextInt();
					if(!ordersService.ordersIdexists(id))break;
					System.out.println("The Total amount for order Id "+id+" = "+ordersService.calculateTotalAmount(id));
					break;
				}catch (ClassNotFoundException| SQLException|OrderIdDoesNotExists e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
				
			case 2:
				try {
					System.out.println("Enter the order ID to fetch the Order Details: ");
					int id = sc.nextInt();
					if(!ordersService.ordersIdexists(id))break;
					
					List <OrdersOrderDetails>list = ordersService.getOrderDetails(id);
					
					for(OrdersOrderDetails ood : list) {
						System.out.println(ood.toString());
					}
					break;
				}catch(ClassNotFoundException| SQLException|OrderIdDoesNotExists e ) {
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
			case 3:
				try {
					System.out.println("Enter the order ID to Update the Order status: ");
					int id = sc.nextInt();
					if(!ordersService.ordersIdexists(id))break;
					
					System.out.println("Pick the new status for the order");
					System.out.println("Press 1. Ordered");
					System.out.println("Press 2. Shipped");
					System.out.println("Press 1. Delivered");
					int opt = sc.nextInt();
					Status newStatus = null;
					if(opt == 1)newStatus = Status.ORDERED;
					else if(opt == 2)newStatus = Status.SHIPPED;
					else if(opt == 3)newStatus = Status.DELIVERED;
					else {
						System.out.println("Enter a valid status next time");
						break;
					}
					
					if(ordersService.updateOrderStatus(id, newStatus))System.out.println("Successfull updated");
					
					break;
				}catch(ClassNotFoundException| SQLException|OrderIdDoesNotExists e ) {
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
			case 4:
				try {
					
					System.out.println("Enter the order ID to Cancel the Order : ");
					int id = sc.nextInt();
					if(!ordersService.ordersIdexists(id))break;
					
					if(!ordersService.cancelOrder(id))System.out.println("Successfull Cancelled the Order");
					
					break;
				}catch(ClassNotFoundException| SQLException|OrderIdDoesNotExists e ) {
					System.out.println(e.getMessage());
					e.printStackTrace();
					
				}
			default:
				System.out.println("Invalid operation !");
				break;
				
			}
			
		}
		sc.close();
		
		
		
	}

}
