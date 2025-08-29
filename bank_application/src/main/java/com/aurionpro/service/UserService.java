package com.aurionpro.service;

import java.util.List;

import com.aurionpro.dao.UserDao;
import com.aurionpro.model.User;

public class UserService {
    private UserDao userDao;

    public UserService() {
        userDao = new UserDao();
    }

    
    public boolean saveCustomer(User user) {
        if (user.getBalance() < 0) {
            user.setBalance(0.0);
        }
        return userDao.saveCustomer(user);
    }

   
    public User login(String username, String password, String loginAs) {
        return userDao.login(username, password, loginAs);
    }


    public List<User> pendingUsers() {
        return userDao.pendingUsers();
    }


    public void updateUserStatus(int userId, String status) {
        userDao.updateUserStatus(userId, status);
    }
}
