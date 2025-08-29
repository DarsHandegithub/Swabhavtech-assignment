package com.aurionpro.controller;

import com.aurionpro.model.Beneficiary;
import com.aurionpro.model.User;
import com.aurionpro.service.BeneficiaryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/BeneficiaryController")
public class BeneficiaryController extends HttpServlet {
    private BeneficiaryService beneficiaryService = new BeneficiaryService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    	HttpSession session = request.getSession(false);
    	if (session == null || session.getAttribute("loggedInUser") == null) {
    	    response.sendRedirect("Login.jsp");
    	    return;
    	}

    	User loggedInUser = (User) session.getAttribute("loggedInUser");
    	int customerId = loggedInUser.getUserId(); 
        String accountNumber = request.getParameter("beneficiaryAccountNumber");
        String name = request.getParameter("beneficiaryName");
        String ifsc = request.getParameter("ifscCode");

        Beneficiary beneficiary = new Beneficiary(0, customerId, accountNumber, name, ifsc);

        boolean added = beneficiaryService.addBeneficiary(beneficiary);

        if (added) {
            response.sendRedirect("BeneficiarySuccess.jsp");
        } else {
            response.sendRedirect("Beneficiary.jsp?error=true");
        }
    }
}
