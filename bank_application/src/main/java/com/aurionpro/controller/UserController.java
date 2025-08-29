package com.aurionpro.controller;

import java.io.IOException;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aurionpro.model.User;
import com.aurionpro.service.UserService;

@WebServlet("/UserController")
public class UserController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserService userService;

    public UserController() {
        super();
        userService = new UserService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String fullName = request.getParameter("full_name");
        String dobStr = request.getParameter("dob");
        String email = request.getParameter("email");
        String mobile = request.getParameter("mobile");
        String address = request.getParameter("address");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String accountType = request.getParameter("account_type");
        String loginAs = request.getParameter("login_as");

        // Parse initial deposit
        double balance = 0.0;
        try {
            String depositStr = request.getParameter("initial_deposit");
            if (depositStr != null && !depositStr.isEmpty()) {
                balance = Double.parseDouble(depositStr);
            }
        } catch (NumberFormatException e) {
            balance = 0.0;
        }

        // Generate unique account number
        long accountNumber = System.currentTimeMillis() + (long)(Math.random() * 1000);

        // Parse date of birth
        Date dob = null;
        try {
            if (dobStr != null && !dobStr.isEmpty()) {
                dob = Date.valueOf(dobStr);
            }
        } catch (IllegalArgumentException e) {
            // handle invalid date
            response.sendRedirect("Register.jsp?error=Invalid Date Format!");
            return;
        }

        // Create user object
        User user = new User();
        user.setFullName(fullName);
        user.setDob(dob);
        user.setEmail(email);
        user.setMobile(mobile);
        user.setAddress(address);
        user.setUsername(username);
        user.setPassword(password);
        user.setAccountType(accountType);
        user.setBalance(balance); 
        user.setLoginAs(loginAs);
        user.setAccountNumber(accountNumber);
        // no need to set status, handled in DAO

        boolean success = userService.saveCustomer(user);

        if (success) {
            response.sendRedirect("Login.jsp?message=Registration Successful! Please wait for admin approval.");
        } else {
            response.sendRedirect("Register.jsp?error=Registration Failed! Try again.");
        }
    }
}
