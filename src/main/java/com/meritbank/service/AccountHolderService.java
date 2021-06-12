package com.meritbank.service;

import java.util.List;

import com.meritbank.exceptions.InvalidArgumentException;
import com.meritbank.exceptions.NoResourceFoundException;
import com.meritbank.model.AccountHolder;
import com.meritbank.model.CDAccount;
import com.meritbank.model.CheckingAccount;
import com.meritbank.model.ContactDetails;
import com.meritbank.model.SavingsAccount;

public interface AccountHolderService {
	public AccountHolder addAccountHolder(AccountHolder accountHolder);
	public AccountHolder getAccountHolder(int id);
	public List<AccountHolder> getAccountHolders();
	public List<CheckingAccount> getCheckingAccounts(int id) throws InvalidArgumentException;
	public List<SavingsAccount> getSavingsAccounts(int id);
	public List<CDAccount> getCDAccounts(int id);
	public ContactDetails addContactDetails(int id, ContactDetails contactDetails);
	public AccountHolder updateAccountHolder(AccountHolder accountHolder);
	public AccountHolder deleteAccountHolder(AccountHolder accountHolder) throws NoResourceFoundException;
}