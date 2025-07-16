package com.aurionpro.model.delivery;

public class Zomato implements IDeliveryPartner {
    @Override
    public void assignDelivery() {
        System.out.println("Order assigned to Zomato.");
    }

    @Override
    public String getPartnerName() {
        return "Zomato";
    }
}
