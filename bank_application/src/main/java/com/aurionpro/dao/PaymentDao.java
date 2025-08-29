package com.aurionpro.dao;

import com.aurionpro.database.Database;
import com.aurionpro.model.Payment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentDao {

    private Connection connection;

    public PaymentDao() {
        connection = Database.connect();
    }

    // Make payment method
    public boolean makePayment(Payment payment) {
        try {
            connection.setAutoCommit(false); // Start transaction

            // 1️⃣ Check sender balance
            PreparedStatement checkBalanceStmt = connection.prepareStatement(
                    "SELECT balance FROM users WHERE user_id = ?"
            );
            checkBalanceStmt.setInt(1, payment.getCustomerId());
            ResultSet rs = checkBalanceStmt.executeQuery();

            if (!rs.next()) {
                connection.rollback();
                return false; // Sender not found
            }

            double senderBalance = rs.getDouble("balance");
            if (senderBalance < payment.getAmount()) {
                connection.rollback();
                return false; // Insufficient balance
            }

            // 2️⃣ Deduct amount from sender
            PreparedStatement deductStmt = connection.prepareStatement(
                    "UPDATE users SET balance = balance - ? WHERE user_id = ?"
            );
            deductStmt.setDouble(1, payment.getAmount());
            deductStmt.setInt(2, payment.getCustomerId());
            deductStmt.executeUpdate();

            // 3️⃣ Get the beneficiary's user_id using beneficiary_account_number
            PreparedStatement beneficiaryStmt = connection.prepareStatement(
                    "SELECT u.user_id FROM users u " +
                    "JOIN beneficiaries b ON u.account_number = b.beneficiary_account_number " +
                    "WHERE b.beneficiary_id = ?"
            );
            beneficiaryStmt.setInt(1, payment.getBeneficiaryId());
            ResultSet beneficiaryRs = beneficiaryStmt.executeQuery();

            if (!beneficiaryRs.next()) {
                connection.rollback();
                return false; // Beneficiary user not found
            }

            int beneficiaryUserId = beneficiaryRs.getInt("user_id");

            // 4️⃣ Credit amount to beneficiary
            PreparedStatement creditStmt = connection.prepareStatement(
                    "UPDATE users SET balance = balance + ? WHERE user_id = ?"
            );
            creditStmt.setDouble(1, payment.getAmount());
            creditStmt.setInt(2, beneficiaryUserId);
            creditStmt.executeUpdate();

            // 5️⃣ Insert payment record
            PreparedStatement insertPayment = connection.prepareStatement(
                    "INSERT INTO payments (customer_id, beneficiary_id, amount) VALUES (?, ?, ?)"
            );
            insertPayment.setInt(1, payment.getCustomerId());
            insertPayment.setInt(2, payment.getBeneficiaryId());
            insertPayment.setDouble(3, payment.getAmount());
            insertPayment.executeUpdate();

            connection.commit();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }

  
}
