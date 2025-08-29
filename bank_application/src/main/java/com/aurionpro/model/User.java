package com.aurionpro.model;

import java.sql.Date;

public class User {
    private int userId;
    private long accountNumber;
    private String fullName;
    private Date dob;
    private String email;
    private String mobile;
    private String address;
    private String username;
    private String password;
    private String accountType;
    private double balance;
    private String loginAs;
    private String status;
    
	public User() {
		super();
	}

	public User(int userId, long accountNumber, String fullName, Date dob, String email, String mobile,
			String address, String username, String password, String accountType, double balance, String loginAs, String status) {
		super();
		this.userId = userId;
		this.accountNumber = accountNumber;
		this.fullName = fullName;
		this.dob = dob;
		this.email = email;
		this.mobile = mobile;
		this.address = address;
		this.username = username;
		this.password = password;
		this.accountType = accountType;
		this.balance = balance;
		this.loginAs = loginAs;
		this.status = status;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getLoginAs() {
		return loginAs;
	}

	public void setLoginAs(String loginAs) {
		this.loginAs = loginAs;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
    
}
