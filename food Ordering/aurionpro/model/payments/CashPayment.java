package com.aurionpro.model.payments;

public class CashPayment implements IPayment {

    @Override
    public void pay(double amount) {
        System.out.println("Paid Rs. " + amount + " via cash.");
    }

	@Override
	public String getPaymentType() {
		// TODO Auto-generated method stub
		return "cash";
	}
}
