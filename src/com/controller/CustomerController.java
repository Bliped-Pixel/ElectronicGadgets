package com.controller;

import java.util.Scanner;
import java.sql.SQLException;
import java.util.List;

import com.dto.CustomerOrdersDto;
import com.model.Customers;
import com.service.CustomerService;

public class CustomerController {
	
	public static void main (String args[]) throws ClassNotFoundException, SQLException
	{
		Scanner sc = new Scanner(System.in);
		CustomerService customerservice = new CustomerService();
		while(true)
		{
			System.out.println("Choose An Operation");
			System.out.println("Press 1. To Calculate the total number of orders placed by A customer");
			System.out.println("Press 2. To Fetch All Customer Details");
			System.out.println("Press 3. TO Update Customer Info");
			System.out.println("Press 0. To Terminate");
			int choice = sc.nextInt();
			if(choice == 0)break;
			switch (choice)
			{
			case 1:
				try {
					List <CustomerOrdersDto> result = customerservice.CalculateTotalOrders();
					System.out.println("Each customer and their total orders");
					for(CustomerOrdersDto co : result)System.out.println(co.toString());
				}catch (ClassNotFoundException | SQLException e) {
					System.out.println(e.getMessage());
				}
				
				break;
			case 2:
				try {
				List <Customers> result = customerservice.GetCustomerDetails();
				System.out.println("All Customers Details");
				for(Customers c : result)System.out.println(c.toString()); 	
				}catch (ClassNotFoundException | SQLException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 3:
				
				try
				{
					
					System.out.println("Enter the ID you want to update");
					int id = sc.nextInt();
					if(!customerservice.idExsist(id))break;
					Customers c = customerservice.fetchCustomerById(id);
					sc.nextLine();
					System.out.println("Enter updated email if not press 0: ");
					String email = sc.nextLine();
					System.out.println("Enter updated phone if not press 0: ");
					String phone = sc.nextLine();
					System.out.println("Enter updated address name if not press 0: ");
					String address = sc.nextLine();
					System.out.println("To confirm the above given details are right press 1");
					int confirm = sc.nextInt();
					if(confirm == 1)
					{
						if(!email.equals("0"))c.setEmail(email);
						if(!phone.equals("0"))c.setPhone(phone);
						if(!address.equals("0"))c.setAddress(address);
						customerservice.updateCustomerInfo(c);
					}
					else break;
					
				}catch (Exception e) {
					e.printStackTrace();
				}
				break;
			default:
				System.out.println("Check the Operation, Invaild choice");
				break;
			}
			
		}
		sc.close();
	}
	
	

}
