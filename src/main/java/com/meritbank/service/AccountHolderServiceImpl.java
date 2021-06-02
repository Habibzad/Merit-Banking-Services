package com.meritbank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meritbank.exceptions.InvalidArgumentException;
import com.meritbank.model.AccountHolder;
import com.meritbank.model.AccountHoldersContactDetails;
import com.meritbank.model.CDAccount;
import com.meritbank.model.CheckingAccount;
import com.meritbank.model.SavingsAccount;
import com.meritbank.repository.AccountHolderRepo;
import com.meritbank.repository.AccountHoldersContactDetailsRepo;


@Service
public class AccountHolderServiceImpl implements AccountHolderService {

	@Autowired
	private AccountHolderRepo accountHolderRepo;
	
	@Autowired
	private AccountHoldersContactDetailsRepo contactDetailsRepo;

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
		return accountHolderRepo.getOne(id);
	}

	@Override
	public List<CheckingAccount> getCheckingAccounts(int id) throws InvalidArgumentException{
		if(accountHolderRepo.existsById(id)) {
			AccountHolder accountHolder = accountHolderRepo.getOne(id);
			return accountHolder.getCheckingAccounts();
		}
		throw new InvalidArgumentException("No such account holder exist");
	}

	@Override
	public List<SavingsAccount> getSavingsAccounts(int id) {
		AccountHolder accountHolder = accountHolderRepo.getOne(id);
		return accountHolder.getSavingsAccounts();
	}

	@Override
	public List<CDAccount> getCDAccounts(int id) {
		AccountHolder accountHolder = accountHolderRepo.getOne(id);
		return accountHolder.getCdAccounts();
	}

	@Override
	public AccountHoldersContactDetails addContactDetails(int id, AccountHoldersContactDetails contactDetails) {
		AccountHolder accountHolder = accountHolderRepo.getOne(id);
		contactDetails.setAccountHolder(accountHolder);
		contactDetailsRepo.save(contactDetails);
		return contactDetails;
	}
	

}
