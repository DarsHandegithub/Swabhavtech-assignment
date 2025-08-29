<%@ page import="java.util.List" %>
<%@ page import="com.aurionpro.model.User" %>
<%@ page import="com.aurionpro.service.UserService" %>

<%
    // ✅ Use implicit session object, do not redeclare
    if (session == null || session.getAttribute("loggedInUser") == null) {
        response.sendRedirect("Login.jsp?error=Unauthorized access");
        return;
    }

    // Fetch pending customers
    UserService userService = new UserService();
    List<User> pendingCustomers = userService.pendingUsers();

    String message = request.getParameter("message");
    String error = request.getParameter("error");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Admin Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container mt-5">

    <div class="d-flex justify-content-between align-items-center mb-4">
        <h2>Pending Customer Approvals</h2>
        <form action="LogoutController" method="post">
            <button type="submit" class="btn btn-secondary">Logout</button>
        </form>
    </div>

    <% if (message != null) { %>
        <div class="alert alert-success"><%= message %></div>
    <% } %>

    <% if (error != null) { %>
        <div class="alert alert-danger"><%= error %></div>
    <% } %>

    <table class="table table-bordered table-striped">
        <thead class="table-dark">
            <tr>
                <th>Full Name</th>
                <th>DOB</th>
                <th>Mobile</th>
                <th>Address</th>
                <th>Username</th>
                <th>Account Type</th>
                <th>Balance</th>
                <th>Status</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <%
                if (pendingCustomers != null && !pendingCustomers.isEmpty()) {
                    for (User u : pendingCustomers) {
            %>
            <tr>
                <td><%= u.getFullName() %></td>
                <td><%= u.getDob() %></td>
                <td><%= u.getMobile() %></td>
                <td><%= u.getAddress() %></td>
                <td><%= u.getUsername() %></td>
                <td><%= u.getAccountType() %></td>
                <td><%= u.getBalance() %></td>
                <td><%= u.getStatus() %></td>
                <td>
                    <a href="AdminController?action=approve&id=<%= u.getUserId() %>" 
                       class="btn btn-success btn-sm">Approve</a>
                    <a href="AdminController?action=reject&id=<%= u.getUserId() %>" 
                       class="btn btn-danger btn-sm">Reject</a>
                </td>
            </tr>
            <%
                    }
                } else {
            %>
            <tr>
                <td colspan="9" class="text-center">No pending customers</td>
            </tr>
            <% } %>
        </tbody>
    </table>
</body>
</html>
