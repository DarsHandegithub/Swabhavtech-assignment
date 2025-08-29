package com.aurionpro.model;

import java.sql.Timestamp;

public class Payment {
    private int paymentId;
    private int customerId;
    private int beneficiaryId;
    private double amount;
    private Timestamp paymentDate;

    
    public Payment() {}

    
    public Payment(int customerId, int beneficiaryId, double amount) {
        this.customerId = customerId;
        this.beneficiaryId = beneficiaryId;
        this.amount = amount;
    }

 
    public Payment(int paymentId, int customerId, int beneficiaryId, double amount, Timestamp paymentDate) {
        this.paymentId = paymentId;
        this.customerId = customerId;
        this.beneficiaryId = beneficiaryId;
        this.amount = amount;
        this.paymentDate = paymentDate;
    }

    
    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getBeneficiaryId() {
        return beneficiaryId;
    }

    public void setBeneficiaryId(int beneficiaryId) {
        this.beneficiaryId = beneficiaryId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Timestamp getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Timestamp paymentDate) {
        this.paymentDate = paymentDate;
    }
}

