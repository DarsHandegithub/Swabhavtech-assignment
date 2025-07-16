package com.aurionpro.model.payments;

public class Payment {
    public static void pay(double amount, String mode) {
        System.out.println("Paid Rs. " + amount + " via " + mode + ".");
        System.out.println("Payment successful. Thank you!");
    }
}
