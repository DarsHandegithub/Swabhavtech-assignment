package com.aurionpro.model.delivery;

public class Swiggy implements IDeliveryPartner {
    @Override
    public void assignDelivery() {
        System.out.println("Order assigned to Swiggy.");
    }

    @Override
    public String getPartnerName() {
        return "Swiggy";
    }
}
