package com.aurionpro.service;

import com.aurionpro.dao.PaymentDao;
import com.aurionpro.model.Payment;

public class PaymentService {

    private PaymentDao paymentDao;

    public PaymentService() {
        paymentDao = new PaymentDao();
    }

    /**
     * Makes a payment from the logged-in customer to a beneficiary.
     * @param payment Payment object containing sender, beneficiary, and amount
     * @return true if payment succeeded, false otherwise
     */
    public boolean makePayment(Payment payment) {
        // You can add additional business logic or validations here if needed
        return paymentDao.makePayment(payment);
    }
}
