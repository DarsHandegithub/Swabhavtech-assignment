<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Beneficiary</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="container mt-5">

    <h2>Add Beneficiary</h2>

    <form action="BeneficiaryController" method="post" class="mt-3">
        <div class="mb-3">
            <label for="beneficiaryAccountNumber" class="form-label">Account Number</label>
            <input type="text" class="form-control" id="beneficiaryAccountNumber" 
                   name="beneficiaryAccountNumber" required>
        </div>

        <div class="mb-3">
            <label for="beneficiaryName" class="form-label">Beneficiary Name</label>
            <input type="text" class="form-control" id="beneficiaryName" 
                   name="beneficiaryName" required>
        </div>

        <div class="mb-3">
            <label for="ifscCode" class="form-label">IFSC Code</label>
            <input type="text" class="form-control" id="ifscCode" 
                   name="ifscCode" required>
        </div>

        <button type="submit" class="btn btn-primary">Add Beneficiary</button>
    </form>

    <%
        if (request.getParameter("error") != null) {
    %>
        <div class="alert alert-danger mt-3">❌ Failed to add beneficiary. Please try again.</div>
    <%
        }
    %>

</body>
</html>
