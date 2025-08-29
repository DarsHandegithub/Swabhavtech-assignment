package com.aurionpro.service;

import java.util.List;

import com.aurionpro.dao.BeneficiaryDao;
import com.aurionpro.model.Beneficiary;

public class BeneficiaryService {

    private BeneficiaryDao beneficiaryDao;

    public BeneficiaryService() {
        beneficiaryDao = new BeneficiaryDao();
    }

    public boolean addBeneficiary(Beneficiary beneficiary) {
    	return beneficiaryDao.addBeneficiary(beneficiary);
    }
    
    public List<Beneficiary> getBeneficiariesByCustomerId(int customerId){
    	return beneficiaryDao.getBeneficiariesByCustomerId(customerId);
    }
 }

 