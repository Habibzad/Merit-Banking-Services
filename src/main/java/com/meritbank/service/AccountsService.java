package com.meritbank.service;

import com.meritbank.exceptions.ExceedsCombinedBalanceLimitException;
import com.meritbank.exceptions.InvalidArgumentException;
import com.meritbank.exceptions.NoSuchAccountException;
import com.meritbank.model.CDAccount;
import com.meritbank.model.CheckingAccount;
import com.meritbank.model.SavingsAccount;

public interface AccountsService {

	public CheckingAccount addCheckingAccount(int id, CheckingAccount checkingAccount) 
			throws NoSuchAccountException, 
			ExceedsCombinedBalanceLimitException, 
			InvalidArgumentException;
	
	public SavingsAccount addSavingsAccount(int id, SavingsAccount savingsAccount) 
			throws NoSuchAccountException, 
			ExceedsCombinedBalanceLimitException, 
			InvalidArgumentException;

	public CDAccount addCDAccount(int id, CDAccount cdAccount) 
			throws NoSuchAccountException, 
			ExceedsCombinedBalanceLimitException, 
			InvalidArgumentException;
}