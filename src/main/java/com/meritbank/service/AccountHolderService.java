package com.meritbank.service;

import java.util.List;

import com.meritbank.exceptions.InvalidArgumentException;
import com.meritbank.model.AccountHolder;
import com.meritbank.model.AccountHoldersContactDetails;
import com.meritbank.model.CDAccount;
import com.meritbank.model.CheckingAccount;
import com.meritbank.model.SavingsAccount;

public interface AccountHolderService {
	public AccountHolder addAccountHolder(AccountHolder accountHolder);
	public AccountHolder getAccountHolder(int id);
	public List<AccountHolder> getAccountHolders();
	public List<CheckingAccount> getCheckingAccounts(int id) throws InvalidArgumentException;
	public List<SavingsAccount> getSavingsAccounts(int id);
	public List<CDAccount> getCDAccounts(int id);
	public AccountHoldersContactDetails addContactDetails(int id, AccountHoldersContactDetails contactDetails);
}