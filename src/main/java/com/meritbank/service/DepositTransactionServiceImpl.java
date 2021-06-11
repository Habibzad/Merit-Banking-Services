package com.meritbank.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.meritbank.repository.BankAccountRepo;
import com.meritbank.repository.DepositTransactionRepo;

public class DepositTransactionServiceImpl implements DepositTransactionService {

	@Autowired 
	private DepositTransactionRepo depositTransactionRepo;
	
	@Autowired BankAccountRepo bankAccountRepo;
	@Override
	public boolean deposit(int accountNumber, double amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
