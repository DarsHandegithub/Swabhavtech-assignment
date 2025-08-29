<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="com.aurionpro.model.User" %>

<%
    HttpSession sessionObj = request.getSession(false);
    if (sessionObj == null || sessionObj.getAttribute("loggedInUser") == null) {
        response.sendRedirect("Login.jsp?error=Please login first");
        return;
    }

    User user = (User) sessionObj.getAttribute("loggedInUser");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Payment Success</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5">
    <div class="alert alert-success text-center">
        <h3>Payment Successful!</h3>
        <p>Thank you, <strong><%= user.getFullName() %></strong>. Your payment has been processed successfully.</p>
        <a href="CustomerDashboard.jsp" class="btn btn-primary mt-3">Back to Dashboard</a>
        <a href="TransactionHistory.jsp" class="btn btn-outline-success mt-3">View Transactions</a>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
