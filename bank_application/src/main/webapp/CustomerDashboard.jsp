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
    String fullName = user.getFullName();
    Double balance = user.getBalance();
    if (balance == null) balance = 0.0;
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Customer Dashboard</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<!-- Navbar -->
<nav class="navbar navbar-expand-lg navbar-dark bg-primary shadow">
  <div class="container-fluid">
    <a class="navbar-brand fw-bold" href="#">MyBank</a>
    <div class="d-flex">
      <span class="navbar-text text-white me-3">
        Welcome, <%= fullName %>
      </span>
      <form action="LogoutController" method="post">
        <button type="submit" class="btn btn-outline-light btn-sm">Logout</button>
      </form>
    </div>
  </div>
</nav>

<!-- Compact Dashboard -->
<div class="container mt-5" style="max-width: 900px;">
    <h2 class="fw-bold text-primary mb-4">Welcome To MyBank</h2>
    
    <!-- Horizontal Button Row -->
    <div class="d-flex flex-wrap gap-2 justify-content-start mb-4">
        <a href="ViewProfile.jsp" class="btn btn-secondary flex-grow-1">View Profile</a>
        <a href="Beneficiary.jsp" class="btn btn-secondary flex-grow-1">Add Beneficiary</a>
        <a href="ViewBeneficiary.jsp" class="btn btn-secondary flex-grow-1">View Beneficiaries</a>
        <a href="TransactionHistory.jsp" class="btn btn-secondary flex-grow-1">View Transactions</a>
        <a href="PaymentController" class="btn btn-secondary flex-grow-1">Make Payment</a>
    </div>

    <!-- Balance Display -->
    <div class="card shadow-sm p-3 text-center" style="width: 300px;">
        <h5 class="card-title">Balance</h5>
        <p class="card-text text-success fs-4"><%= balance %></p>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
