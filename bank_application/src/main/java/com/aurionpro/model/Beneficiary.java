package com.aurionpro.model;

public class Beneficiary {
	private int beneficiaryId;
	private int customerId;
	private String accountNumber;
	private String beneficiaryName;
	private String ifscCode;
	
	public Beneficiary() {
		super();
	}

	public Beneficiary(int beneficiaryId, int customerId, String accountNumber, String beneficiaryName, 
			String ifscCode) {
		super();
		this.beneficiaryId = beneficiaryId;
		this.customerId = customerId;
		this.accountNumber = accountNumber;
		this.beneficiaryName = beneficiaryName;
		this.ifscCode = ifscCode;
	}

	public int getBeneficiaryId() {
		return beneficiaryId;
	}

	public void setBeneficiaryId(int beneficiaryId) {
		this.beneficiaryId = beneficiaryId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getBeneficiaryName() {
		return beneficiaryName;
	}

	public void setBeneficiaryName(String beneficiaryName) {
		this.beneficiaryName = beneficiaryName;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}
}
