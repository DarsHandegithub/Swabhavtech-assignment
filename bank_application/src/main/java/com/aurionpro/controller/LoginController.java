package com.aurionpro.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aurionpro.model.User;
import com.aurionpro.service.UserService;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserService userService;

    public LoginController() {
        super();
        userService = new UserService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String loginAs = request.getParameter("login_as");

        User user = userService.login(username, password, loginAs);

        if (user != null) {
            // Check status for customers
            if ("customer".equalsIgnoreCase(user.getLoginAs()) && !"APPROVED".equalsIgnoreCase(user.getStatus())) {
                
                response.sendRedirect("Login.jsp?error=Your account is " + user.getStatus() + ". Please contact admin.");
                return;
            }

            HttpSession session = request.getSession();
            session.setAttribute("loggedInUser", user); // store entire user object

            // Redirect based on role
            if ("customer".equalsIgnoreCase(user.getLoginAs())) {
                response.sendRedirect("CustomerDashboard.jsp");
            } else if ("admin".equalsIgnoreCase(user.getLoginAs())) {
                response.sendRedirect("AdminDashboard.jsp");
            }
        } else {
            response.sendRedirect("Login.jsp?error=Invalid Username or Password");
        }
    }
}
