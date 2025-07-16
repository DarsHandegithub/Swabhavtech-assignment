package com.aurionpro.model.payments;

import java.util.Scanner;

public class CardPayment implements IPayment {
	private String paymentType;

	public CardPayment() {
		this.paymentType = "Card";
	}

	@Override
	public void pay(double amount) {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Enter 12 digit Credit Card number:");
		String cardNumber = scanner.nextLine();
		while (!cardNumber.matches("\\d{12}")) {
			System.out.println("Invalid card number. It must be exactly 12 digits. Please re-enter:");
			cardNumber = scanner.nextLine();
		}

		System.out.println("Enter expiry year of your card:");
		int expiryDate = scanner.nextInt();
		scanner.nextLine(); // consume newline
		while (expiryDate < 2025 || expiryDate > 2029) {
			System.out.println("Invalid expiry year. Enter between 2025 to 2029:");
			expiryDate = scanner.nextInt();
			scanner.nextLine();
		}

		System.out.println("Enter CVV:");
		String cvv = scanner.nextLine();
		while (!cvv.matches("\\d{3}")) {
			System.out.println("Invalid CVV. It must be exactly 3 digits. Please re-enter:");
			cvv = scanner.nextLine();
		}
		System.out.println("Card Payment of Rs." + amount + " successful.");
	}

	@Override
	public String getPaymentType() {
		return paymentType;
	}
}
