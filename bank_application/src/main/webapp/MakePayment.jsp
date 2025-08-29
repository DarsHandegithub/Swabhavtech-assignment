<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="com.aurionpro.model.User" %>
<%@ page import="com.aurionpro.model.Beneficiary" %>
<%@ page import="java.util.List" %>

<%
    HttpSession sessionObj = request.getSession(false);
    if (sessionObj == null || sessionObj.getAttribute("loggedInUser") == null) {
        response.sendRedirect("Login.jsp?error=Please login first");
        return;
    }

    User user = (User) sessionObj.getAttribute("loggedInUser");
    List<Beneficiary> beneficiaries = (List<Beneficiary>) request.getAttribute("beneficiaries");
    if (beneficiaries == null) beneficiaries = new java.util.ArrayList<>();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Make Payment</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5">
    <h3>Make Payment</h3>

    <% if(request.getParameter("error") != null) { %>
        <div class="alert alert-danger"><%= request.getParameter("error") %></div>
    <% } %>

    <form action="PaymentController" method="post">
        <div class="mb-3">
            <label for="beneficiaryId" class="form-label">Select Beneficiary</label>
            <select name="beneficiaryId" id="beneficiaryId" class="form-select" required>
                <option value="">--Select--</option>
                <% for(Beneficiary b : beneficiaries) { %>
                    <option value="<%= b.getBeneficiaryId() %>">
                        <%= b.getBeneficiaryName() %> ( <%= b.getAccountNumber() %> )
                    </option>
                <% } %>
            </select>
        </div>

        <div class="mb-3">
            <label for="amount" class="form-label">Amount</label>
            <input type="number" name="amount" id="amount" class="form-control" min="1" step="0.01" required>
        </div>

        <button type="submit" class="btn btn-primary">Pay Now</button>
    </form>

    <a href="CustomerDashboard.jsp" class="btn btn-link mt-3">Back to Dashboard</a>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
