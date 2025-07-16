package com.aurionpro.model.payments;

public interface IPayment {
    void pay(double amount);
    String getPaymentType();
}
