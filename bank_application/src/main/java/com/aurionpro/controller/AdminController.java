package com.aurionpro.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aurionpro.service.UserService;

@WebServlet("/AdminController")
public class AdminController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserService userService = new UserService();

    private static final String STATUS_APPROVED = "APPROVED";
    private static final String STATUS_REJECTED = "REJECTED";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loggedInUser") == null) {
            response.sendRedirect("Login.jsp?error=Unauthorized access");
            return;
        }

        String action = request.getParameter("action"); 
        String idStr = request.getParameter("id");       

        if (idStr == null || idStr.trim().isEmpty()) {
            response.sendRedirect("AdminDashboard.jsp?error=Invalid user ID");
            return;
        }

        int userId = Integer.parseInt(idStr);
        String message;

      
        if ("approve".equalsIgnoreCase(action)) {
            userService.updateUserStatus(userId, STATUS_APPROVED);
            message = "User with ID " + userId + " has been " + STATUS_APPROVED;
        } else if ("reject".equalsIgnoreCase(action)) {
            userService.updateUserStatus(userId, STATUS_REJECTED);
            message = "User with ID " + userId + " has been " + STATUS_REJECTED;
        } else {
            response.sendRedirect("AdminDashboard.jsp?error=Invalid action");
            return;
        }

        
        response.sendRedirect("AdminDashboard.jsp?message=" + message);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doGet(request, response);
    }
}
