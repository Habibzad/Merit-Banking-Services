package com.meritbank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meritbank.exceptions.InvalidArgumentException;
import com.meritbank.exceptions.NoResourceFoundException;
import com.meritbank.model.AccountHolder;
import com.meritbank.model.CDAccount;
import com.meritbank.model.CheckingAccount;
import com.meritbank.model.ContactDetails;
import com.meritbank.model.SavingsAccount;
import com.meritbank.repository.AccountHolderRepo;
import com.meritbank.repository.ContactDetailsRepo;


@Service
public class AccountHolderServiceImpl implements AccountHolderService {

	@Autowired
	private AccountHolderRepo accountHolderRepo;
	
	@Autowired
	private ContactDetailsRepo contactDetailsRepo;

	@Override
	public AccountHolder addAccountHolder(AccountHolder accountHolder) {
		return accountHolderRepo.save(accountHolder);
	}

	@Override
	public List<AccountHolder> getAccountHolders() {
		return accountHolderRepo.findAll();
	}

	@Override
	public AccountHolder getAccountHolder(int id) {
		return accountHolderRepo.getById(id);
	}

	@Override
	public List<CheckingAccount> getCheckingAccounts(int id) throws InvalidArgumentException{
		if(accountHolderRepo.existsById(id)) {
			AccountHolder accountHolder = accountHolderRepo.getById(id);
			return accountHolder.getCheckingAccounts();
		}
		throw new InvalidArgumentException("No such account holder exist");
	}

	@Override
	public List<SavingsAccount> getSavingsAccounts(int id) {
		AccountHolder accountHolder = accountHolderRepo.getById(id);
		return accountHolder.getSavingsAccounts();
	}

	@Override
	public List<CDAccount> getCDAccounts(int id) {
		AccountHolder accountHolder = accountHolderRepo.getById(id);
		return accountHolder.getCdAccounts();
	}

	@Override
	public ContactDetails addContactDetails(int id, ContactDetails contactDetails) {
		AccountHolder accountHolder = accountHolderRepo.getById(id);
		contactDetails.setAccountHolder(accountHolder);
		contactDetailsRepo.save(contactDetails);
		return contactDetails;
	}

	@Override
	public AccountHolder updateAccountHolder(AccountHolder accountHolder) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public AccountHolder deleteAccountHolder(AccountHolder accountHolder) throws NoResourceFoundException{
		AccountHolder ach = accountHolderRepo.getById(accountHolder.getId());
		if(ach!=null) {
			accountHolderRepo.delete(ach);
			return accountHolder;
		} else {
			throw new NoResourceFoundException("Account Does Not Exist");
		}
	}
}
