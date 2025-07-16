package com.aurionpro.model.payments;

import java.util.Scanner;

public class UpiPayment implements IPayment {
    private String paymentType;
    private static final String validPin = "1234";

    public UpiPayment() {
        this.paymentType = "UPI";
    }

    @Override
    public void pay(double amount) {
        Scanner scanner = new Scanner(System.in);

        String upiId;
        while (true) {
            System.out.println("Enter your UPI ID (format: name@okbank): ");
            upiId = scanner.nextLine();

            if (upiId.matches("^[a-zA-Z0-9._-]+@ok[a-zA-Z]+$")) {
                break;
            } else {
                System.out.println("Invalid UPI ID. Please enter in correct format (e.g. name@okbankname).");
            }
        }

        while (true) {
            System.out.println("Enter your UPI PIN: ");
            String upiPin = scanner.nextLine();

            if (upiPin.equals(validPin)) {
                System.out.println("UPI Payment of Rs." + amount + " successful.");
                break;
            } else {
                System.out.println("Incorrect PIN. Payment failed. Please try again.");
            }
        }
    }

    @Override
    public String getPaymentType() {
        return paymentType;
    }
}
