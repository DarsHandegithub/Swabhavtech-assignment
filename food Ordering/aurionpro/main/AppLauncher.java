package com.aurionpro.main;

import java.util.InputMismatchException;
import java.util.Scanner;
import com.aurionpro.model.customer.Customer;
import com.aurionpro.model.admin.Admin;

public class AppLauncher {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		while (true) {
			try {
				System.out.println("===== Welcome to Food Ordering System =====");
				System.out.println("1. Admin");
				System.out.println("2. Customer");
				System.out.println("3. Exit");
				System.out.print("Enter your choice: ");

				int choice = scanner.nextInt();
				scanner.nextLine(); 

				switch (choice) {
				case 1:
					Admin admin = new Admin();
					admin.start();
					break;

				case 2:
					Customer customer = new Customer();
					customer.start(); 
					break;

				case 3:
					System.out.println("Thank you for using the Food Ordering System. Goodbye!");
					scanner.close();
					System.exit(0);
					break;

				default:
					System.out.println("Invalid choice. Please try again.");
				}
			} catch (InputMismatchException exception) {
				System.out.println("Invalid input. Please enter a number choice.");
				scanner.nextLine(); 
			} catch (Exception exception) {
				System.out.println("An unexpected error occurred: " + exception.getMessage());
			}
		}
	}
}
