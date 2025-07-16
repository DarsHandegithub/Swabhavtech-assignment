package com.aurionpro.model.discount;

public interface IDiscount {
    double applyDiscount(double total);
    String getDiscountType();
}
