package com.aurionpro.model.invoice;

import java.util.List;
import com.aurionpro.model.order.CartItem;
import com.aurionpro.model.delivery.IDeliveryPartner;

public class Invoice {
	private String customerName;
	private String phone;
	private String address;
	private List<CartItem> cartItems;
	private double discountedTotal;
	private double cgst;
	private double sgst;
	private double grandTotal;
	private IDeliveryPartner deliveryPartner;

	public Invoice(String customerName, String phone, String address, List<CartItem> cartItems, double discountedTotal,
			double cgst, double sgst, double grandTotal, IDeliveryPartner deliveryPartner) {
		this.customerName = customerName;
		this.phone = phone;
		this.address = address;
		this.cartItems = cartItems;
		this.discountedTotal = discountedTotal;
		this.cgst = cgst;
		this.sgst = sgst;
		this.grandTotal = grandTotal;
		this.deliveryPartner = deliveryPartner;
	}

	public void printInvoice() {
		System.out.println("\n==================== FINAL INVOICE ====================");
		System.out.printf("Customer : %s%n", customerName);
		System.out.printf("Phone    : %s%n", phone);
		System.out.printf("Address  : %s%n", address);
		System.out.println("-------------------------------------------------------");
		System.out.printf("%-30s %5s %10s%n", "Item", "Qty", "Subtotal");
		System.out.println("-------------------------------------------------------");

		for (CartItem ci : cartItems) {
			double subTotal = ci.getFoodItem().getPrice() * ci.getQuantity();
			System.out.printf("%-30s %5d %10s%.2f%n", ci.getFoodItem().getName(), ci.getQuantity(), "₹", subTotal);
		}

		System.out.println("-------------------------------------------------------");
		System.out.printf("%-36s %10s%.2f%n", "Discounted Total:", "₹", discountedTotal);
		System.out.printf("%-36s %10s%.2f%n", "CGST @2.5%:", "₹", cgst);
		System.out.printf("%-36s %10s%.2f%n", "SGST @2.5%:", "₹", sgst);
		System.out.printf("%-36s %10s%.2f%n", "Grand Total:", "₹", grandTotal);
		System.out.println("-------------------------------------------------------");
		System.out.printf("Delivery Partner: %s%n", deliveryPartner.getPartnerName());
		System.out.println("=======================================================\n");
	}
}
