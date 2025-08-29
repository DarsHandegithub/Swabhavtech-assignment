package com.aurionpro.controller;

import com.aurionpro.model.Payment;
import com.aurionpro.model.User;
import com.aurionpro.model.Beneficiary;
import com.aurionpro.service.PaymentService;
import com.aurionpro.service.BeneficiaryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/PaymentController")
public class PaymentController extends HttpServlet {

    private PaymentService paymentService = new PaymentService();
    private BeneficiaryService beneficiaryService = new BeneficiaryService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Check if user is logged in
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loggedInUser") == null) {
            response.sendRedirect("Login.jsp?error=Please login first");
            return;
        }

        User loggedInUser = (User) session.getAttribute("loggedInUser");
        int customerId = loggedInUser.getUserId();

        // Load the customer's beneficiaries for the dropdown
        List<Beneficiary> beneficiaries = beneficiaryService.getBeneficiariesByCustomerId(customerId);
        request.setAttribute("beneficiaries", beneficiaries);

        // Forward to MakePayment.jsp
        request.getRequestDispatcher("MakePayment.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loggedInUser") == null) {
            response.sendRedirect("Login.jsp?error=Please login first");
            return;
        }

        User loggedInUser = (User) session.getAttribute("loggedInUser");
        int customerId = loggedInUser.getUserId();

        try {
            int beneficiaryId = Integer.parseInt(request.getParameter("beneficiaryId"));
            double amount = Double.parseDouble(request.getParameter("amount"));

            Payment payment = new Payment(customerId, beneficiaryId, amount);

            boolean success = paymentService.makePayment(payment);

            if (success) {
                response.sendRedirect("PaymentSuccess.jsp");
            } else {
                response.sendRedirect("MakePayment.jsp?error=Payment failed. Check balance or beneficiary.");
            }

        } catch (NumberFormatException e) {
            response.sendRedirect("MakePayment.jsp?error=Invalid input for amount.");
        }
    }
}
