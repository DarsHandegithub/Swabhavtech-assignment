package com.aurionpro.controller;

import com.aurionpro.model.Beneficiary;
import com.aurionpro.model.User;
import com.aurionpro.service.BeneficiaryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/ViewBeneficiaryController")
public class ViewBeneficiaryController extends HttpServlet {
    private BeneficiaryService service = new BeneficiaryService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loggedInUser") == null) {
            response.sendRedirect("Login.jsp");
            return;
        }

        User loggedInUser = (User) session.getAttribute("loggedInUser");
        int customerId = loggedInUser.getUserId();
        System.out.println("Logged-in customerId: " + customerId); // debug

        List<Beneficiary> beneficiaries = service.getBeneficiariesByCustomerId(customerId);
        System.out.println("Number of beneficiaries retrieved: " + beneficiaries.size()); // debug
        for (Beneficiary b : beneficiaries) {
            System.out.println(
                b.getBeneficiaryId() + " | " +
                b.getAccountNumber() + " | " +
                b.getBeneficiaryName() + " | " +
                b.getIfscCode()
            );
        }

        request.setAttribute("beneficiaries", beneficiaries);
        request.getRequestDispatcher("ViewBeneficiary.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
