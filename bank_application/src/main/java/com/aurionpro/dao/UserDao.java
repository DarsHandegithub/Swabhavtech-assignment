package com.aurionpro.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aurionpro.database.Database;
import com.aurionpro.model.User;

public class UserDao {
    private Connection connection;

    public UserDao() {
        connection = Database.connect();
    }

    public boolean saveCustomer(User user) {
        String sql = "INSERT INTO users (full_name, dob, email, mobile, address, username, password, account_type, balance, login_as, account_number, status) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 'PENDING')";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, user.getFullName());
            ps.setDate(2, user.getDob());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getMobile());
            ps.setString(5, user.getAddress());
            ps.setString(6, user.getUsername());
            ps.setString(7, user.getPassword());
            ps.setString(8, user.getAccountType());
            ps.setDouble(9, user.getBalance());
            ps.setString(10, user.getLoginAs());
            ps.setLong(11, user.getAccountNumber());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

   
    public User login(String username, String password, String loginAs) {
        User user = null;
        String sql = "SELECT * FROM users WHERE username=? AND password=? AND login_as=? AND status='APPROVED'";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, loginAs);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    user = new User();
                    user.setUserId(rs.getInt("user_id"));
                    user.setUsername(rs.getString("username"));
                    user.setFullName(rs.getString("full_name"));
                    user.setBalance(rs.getDouble("balance"));
                    user.setLoginAs(rs.getString("login_as"));
                    user.setStatus(rs.getString("status"));
                  
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    // Get all pending customers
    public List<User> pendingUsers() {
        List<User> pendingUsers = new ArrayList<>();
        String sql = "SELECT * FROM users WHERE login_as = 'customer' AND UPPER(status) = 'PENDING'";
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setUsername(rs.getString("username"));
                user.setFullName(rs.getString("full_name"));
                user.setDob(rs.getDate("dob"));          
                user.setMobile(rs.getString("mobile"));    
                user.setAddress(rs.getString("address"));  
                user.setAccountType(rs.getString("account_type")); 
                user.setEmail(rs.getString("email"));
                user.setBalance(rs.getDouble("balance"));
                user.setStatus(rs.getString("status"));
                user.setLoginAs(rs.getString("login_as"));
                pendingUsers.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pendingUsers;
    }

    // Approve or reject user by updating status
    public void updateUserStatus(int userId, String newStatus) {
        String sql = "UPDATE users SET status = ? WHERE user_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, newStatus);
            ps.setInt(2, userId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
