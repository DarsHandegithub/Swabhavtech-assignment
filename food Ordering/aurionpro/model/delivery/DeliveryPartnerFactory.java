package com.aurionpro.model.delivery;

import java.util.Random;

public class DeliveryPartnerFactory {
    public static IDeliveryPartner getRandomPartner() {
        Random random = new Random();
        if (random.nextBoolean())
            return new Swiggy();
        else
            return new Zomato();
    }
}
