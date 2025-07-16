package com.aurionpro.model.admin;

import java.util.InputMismatchException;
import java.util.Scanner;
import com.aurionpro.model.menu.Menu;
import com.aurionpro.model.food.FoodItem;
import com.aurionpro.model.appinitializer.*;

public class Admin {
	private static final String USERNAME = "admin";
	private static final String PASSWORD = "password@123";

	public void start() {
		Scanner scanner = new Scanner(System.in);

		try {
			System.out.println("Enter Admin Username:");
			String uname = scanner.nextLine();
			System.out.println("Enter Admin Password:");
			String pass = scanner.nextLine();

			if (!USERNAME.equals(uname) || !PASSWORD.equals(pass)) {
				System.out.println("Invalid credentials. Access denied.");
				return;
			}

			Menu menu = AppInitializer.getMenu();

			while (true) {
				try {
					System.out.println("\n--- Admin Panel ---");
					System.out.println("1. View Menu");
					System.out.println("2. Add Item");
					System.out.println("3. Remove Item");
					System.out.println("4. Logout");
					System.out.print("Enter your choice: ");
					int choice = scanner.nextInt();
					scanner.nextLine();

					switch (choice) {
					case 1:
						try {
							System.out.println("Choose Cuisine to view:");
							System.out.println("1. Indian");
							System.out.println("2. Chinese");
							int cuisineChoice = scanner.nextInt();
							scanner.nextLine();
							
							String cuisineView;
							if(cuisineChoice == 1) {
								cuisineView = "Indian";
							}
							else {
								cuisineView = "Chinese";
							}

							System.out.println("=== Current Menu (" + cuisineView + ") ===");
							System.out.printf("%-5s %-30s %-10s %-15s%n", "ID", "Name", "Price", "Category");
							System.out.println("---------------------------------------------------------------------");

							for (FoodItem item : menu.getItems()) {
								if (item.getCuisine().equalsIgnoreCase(cuisineView)) {
									System.out.printf("%-5d %-30s ₹.%-8.2f %-15s%n", item.getId(), item.getName(),
											item.getPrice(), item.getCategory());
								}
							}
						} catch (InputMismatchException exception) {
							System.out.println("Invalid input for cuisine choice.");
							scanner.nextLine();
						}
						break;

					case 2:
						try {
							System.out.println("Select Cuisine for new item:");
							System.out.println("1. Indian");
							System.out.println("2. Chinese");
							int cuisineAddChoice = scanner.nextInt();
							scanner.nextLine();

							String cuisine;
							if (cuisineAddChoice == 1) {
								cuisine = "Indian";
							} else if (cuisineAddChoice == 2) {
								cuisine = "Chinese";
							} else {
								System.out.println("Invalid cuisine choice. Item not added.");
								break;
							}

							System.out.println("Enter new item ID:");
							int id = scanner.nextInt();
							scanner.nextLine();

							boolean exists = false;
							for (FoodItem foodItem : menu.getItems()) {
								if (foodItem.getId() == id && foodItem.getCuisine().equalsIgnoreCase(cuisine)) {
									exists = true;
									break;
								}
							}

							if (exists) {
								System.out.println(
										"Item ID already exists in " + cuisine + " cuisine. Cannot add duplicate.");
								break;
							}

							System.out.println("Enter item name:");
							String name = scanner.nextLine();

							System.out.println("Enter item price:");
							double price = scanner.nextDouble();
							scanner.nextLine();

							System.out.println("Enter item category (Starter/MainCourse/Dessert):");
							String category = scanner.nextLine();

							FoodItem newItem = new FoodItem(id, name, price, category, cuisine);
							menu.addItem(newItem);
							System.out.println("Item added successfully to " + cuisine + " cuisine.");
						} catch (InputMismatchException exception) {
							System.out.println("Invalid input. Please enter correct data types.");
							scanner.nextLine(); 
						}
						break;

					case 3:
						try {
							System.out.println("Select Cuisine for item to remove:");
							System.out.println("1. Indian");
							System.out.println("2. Chinese");
							int cuisineRemoveChoice = scanner.nextInt();
							scanner.nextLine();

							String cuisineRemove;
							if (cuisineRemoveChoice == 1) {
								cuisineRemove = "Indian";
							} else if (cuisineRemoveChoice == 2) {
								cuisineRemove = "Chinese";
							} else {
								System.out.println("Invalid cuisine choice. Cannot remove item.");
								break;
							}

							System.out.println("Enter Item ID to remove:");
							int removeId = scanner.nextInt();
							scanner.nextLine();

							boolean removed = false;
							for (FoodItem foodItem : menu.getItems()) {
								if (foodItem.getId() == removeId
										&& foodItem.getCuisine().equalsIgnoreCase(cuisineRemove)) {
									menu.removeItem(removeId, cuisineRemove);
									removed = true;
									break;
								}
							}

							if (removed) {
								System.out.println("Item removed successfully from " + cuisineRemove + " cuisine.");
							} else {
								System.out.println("Item not found in " + cuisineRemove + " cuisine.");
							}
						} catch (InputMismatchException exception) {
							System.out.println("Invalid input. Please enter correct data types.");
							scanner.nextLine();
						}
						break;

					case 4:
						System.out.println("Logging out...");
						return;

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
		} catch (Exception exception) {
			System.out.println("Error during login or initialization. " + exception.getMessage());
		}
	}
}
