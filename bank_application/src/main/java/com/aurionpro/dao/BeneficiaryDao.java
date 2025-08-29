package com.aurionpro.dao;

import java.sql.*;
import com.aurionpro.database.Database;
import com.aurionpro.model.Beneficiary;
import java.util.ArrayList;
import java.util.List;

public class BeneficiaryDao {
	private Connection connection;
	private PreparedStatement statement;

	public BeneficiaryDao() {
		connection = Database.connect();
	}

	public boolean addBeneficiary(Beneficiary beneficiary) {
		try {
			statement = connection.prepareStatement("INSERT INTO beneficiaries "
					+ "(customer_id, beneficiary_account_number, beneficiary_name, ifsc_code) VALUES (?, ?, ?, ?)");
			statement.setInt(1, beneficiary.getCustomerId());
			statement.setString(2, beneficiary.getAccountNumber());
			statement.setString(3, beneficiary.getBeneficiaryName());
			statement.setString(4, beneficiary.getIfscCode());

			int rows = statement.executeUpdate();
			return rows > 0;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public List<Beneficiary> getBeneficiariesByCustomerId(int customerId) {
	    List<Beneficiary> list = new ArrayList<>();
	    try {
	    	PreparedStatement statement = connection.prepareStatement("SELECT * FROM beneficiaries WHERE customer_id = ?");
	        statement.setInt(1, customerId);
	        ResultSet result = statement.executeQuery();

	        while (result.next()) {
	            Beneficiary beneficiary = new Beneficiary();
	            beneficiary.setBeneficiaryId(result.getInt("beneficiary_id"));
	            beneficiary.setCustomerId(result.getInt("customer_id"));
	            beneficiary.setAccountNumber(result.getString("beneficiary_account_number"));
	            beneficiary.setBeneficiaryName(result.getString("beneficiary_name"));
	            beneficiary.setIfscCode(result.getString("ifsc_code"));

	            list.add(beneficiary); 
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return list;
	}

}
