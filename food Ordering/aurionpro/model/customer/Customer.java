package com.aurionpro.model.customer;

import java.util.InputMismatchException;
import java.util.Scanner;
import com.aurionpro.model.appinitializer.*;
import com.aurionpro.model.menu.Menu;
import com.aurionpro.model.food.FoodItem;
import com.aurionpro.model.order.Order;
import com.aurionpro.model.order.CartItem;
import com.aurionpro.model.discount.FlatDiscount;
import com.aurionpro.model.payments.*;
import com.aurionpro.model.delivery.IDeliveryPartner;
import com.aurionpro.model.delivery.Swiggy;
import com.aurionpro.model.delivery.Zomato;
import java.util.Random;
import com.aurionpro.model.invoice.*;

public class Customer {
	private String name;
	private String phone;
	private String address;
	private Order order;
	
	private static final double CGST_RATE = 0.025;
	private static final double SGST_RATE = 0.025;

	public Customer() {
		this.order = new Order();
	}

	public void start() {
		Scanner scanner = new Scanner(System.in);

		try {
			
			while (true) {
				System.out.println("Enter your name:");
				this.name = scanner.nextLine();
				if (!this.name.matches(".*\\d.*")) {
					break;
				} else {
					System.out.println("Invalid name. Name must not contain numbers.");
				}
			}
			while (true) {
				System.out.println("Enter your phone:");
				this.phone = scanner.nextLine();
				if (this.phone.matches("\\d{10}")) {
					break;
				} else {
					System.out.println("Invalid phone number. It must be exactly 10 digits.");
				}
			}
			System.out.println("Enter your address:");
			this.address = scanner.nextLine();

		} catch (Exception exception) {
			System.out.println("Error in input. Please restart.");
			return;
		}

		Menu menu = AppInitializer.getMenu(); 

		while (true) {
			try {
				System.out.println("\n--- MENU ---");
				System.out.println("1. View Menu");
				System.out.println("2. Add Item to Cart");
				System.out.println("3. View Cart");
				System.out.println("4. Generate Bill & Pay");
				//System.out.println("5. Print Invoice");
				System.out.println("5. Exit to Main Menu");
				System.out.print("Enter your choice: ");

				int choice = scanner.nextInt();
				scanner.nextLine();

				switch (choice) {
				case 1:
					try {
						System.out.println("Choose Cuisine: 1. Indian 2. Chinese");
						int cuisineChoice = scanner.nextInt();
						scanner.nextLine(); 

						String cuisine; 
						if(cuisineChoice == 1) {
							cuisine = "Indian";
						}
						else {
							cuisine = "Chinese";
						}
						
						System.out.println(cuisine + " Menu:");
						System.out.printf("%-5s %-30s %-10s %-15s%n", "ID", "Name", "Price", "Category");
						System.out.println("---------------------------------------------------------------------");

						for (FoodItem item : menu.getItems()) {
							if (item.getCuisine().equalsIgnoreCase(cuisine)) {
								System.out.printf("%-5d %-30s ₹.%-8.2f %-15s%n", item.getId(), item.getName(),
										item.getPrice(), item.getCategory());
							}
						}
					} catch (InputMismatchException exception) {
						System.out.println("Invalid input for cuisine choice.");
						scanner.nextLine(); // 
					}
					break;

				case 2:
					try {
						System.out.println("Enter Item ID to add:");
						int id = scanner.nextInt();
						System.out.println("Enter Quantity:");
						int qty = scanner.nextInt();
						scanner.nextLine(); 

						FoodItem itemToAdd = menu.getItemById(id);
						if (itemToAdd != null) {
							order.addItem(itemToAdd, qty);
							System.out.println("Item added to cart.");
						} else {
							System.out.println("Invalid Item ID.");
						}
					} catch (InputMismatchException exception) {
						System.out.println("Invalid input. Please enter valid integers.");
						scanner.nextLine(); 
					}
					break;

				case 3:
					if (order.getCartItems().isEmpty()) {
						System.out.println("Cart is empty.");
					} else {
						System.out.println("==== Cart Items ====");
						System.out.printf("%-5s %-30s %-10s %-10s %-10s%n", "No.", "Item Name", "Price(₹)", "Quantity",
								"Subtotal(₹)");
						System.out
								.println("--------------------------------------------------------------------------");

						int count = 1;
						for (CartItem ci : order.getCartItems()) {
							double subTotal = ci.getFoodItem().getPrice() * ci.getQuantity();
							System.out.printf("%-5d %-30s %-10.2f %-10d %-10.2f%n", count++, ci.getFoodItem().getName(),
									ci.getFoodItem().getPrice(), ci.getQuantity(), subTotal);
						}

						System.out
								.println("--------------------------------------------------------------------------");
						System.out.printf("%-57s %-10.2f%n", "Total:", order.getTotal());
					}
					break;

				case 4:
					if (order.getCartItems().isEmpty()) {
						System.out.println("Cart is empty. Please add items before generating bill.");
						break;
					}

					try {
						double discountedTotal = FlatDiscount.apply(order.getTotal());
						double cgst = discountedTotal * 0.025;
						double sgst = discountedTotal * 0.025;
						double grandTotal = discountedTotal + cgst + sgst;

						System.out.println("Payable amount: ₹." + grandTotal);

						System.out.println("Choose payment mode: Cash / Card / UPI");
						String mode = scanner.nextLine();

						IPayment payment;
						switch (mode.toLowerCase()) {
						case "cash":
							payment = new CashPayment();
							break;
						case "card":
							payment = new CardPayment();
							break;
						case "upi":
							payment = new UpiPayment();
							break;
						default:
							System.out.println("Invalid payment mode.");
							continue;
						}

						payment.pay(grandTotal);
						System.out.println("Payment successful. Thank you!");

						IDeliveryPartner deliveryPartner;
						if (new Random().nextBoolean()) {
							deliveryPartner = new Swiggy();
						} else {
							deliveryPartner = new Zomato();
						}
						deliveryPartner.assignDelivery();

						Invoice invoice = new com.aurionpro.model.invoice.Invoice(this.name, this.phone, this.address,
								order.getCartItems(), discountedTotal, cgst, sgst, grandTotal, deliveryPartner);

						invoice.printInvoice();

						//order.clearCart(); 

					} catch (Exception exception) {
						System.out.println("Payment failed. Please try again.");
					}
					break;
					
				case 5:
					System.out.println("Returning to main menu...");
					return;

				default:
					System.out.println("Invalid choice. Please choose between 1-6.");
				}
			} catch (InputMismatchException exception) {
				System.out.println("Invalid input. Please enter a valid choice.");
				scanner.nextLine(); 
			} catch (Exception exception) {
				System.out.println("An unexpected error occurred: " + exception.getMessage());
			}
		}
	}
}
