<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>My Beneficiaries</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h2>My Beneficiaries</h2>
        <hr/>

        <!-- Show message if no beneficiaries -->
        <c:if test="${empty beneficiaries}">
            <div class="alert alert-info">No beneficiaries yet.</div>
        </c:if>

        <!-- Display table if beneficiaries exist -->
        <c:if test="${not empty beneficiaries}">
            <table class="table table-bordered table-striped">
                <thead class="table-dark">
                    <tr>
                        <th>ID</th>
                        <th>Account Number</th>
                        <th>Name</th>
                        <th>IFSC Code</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="b" items="${beneficiaries}">
                        <tr>
                            <td>${b.beneficiaryId}</td>
                            <td>${b.accountNumber}</td>
                            <td>${b.beneficiaryName}</td>
                            <td>${b.ifscCode}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>

        <a href="CustomerDashboard.jsp" class="btn btn-primary mt-3">Back to Dashboard</a>
    </div>
</body>
</html>
