package com.meritbank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meritbank.exceptions.ExceedsCombinedBalanceLimitException;
import com.meritbank.exceptions.InvalidArgumentException;
import com.meritbank.exceptions.NoSuchAccountException;
import com.meritbank.model.AccountHolder;
import com.meritbank.model.CDAccount;
import com.meritbank.model.CDOffering;
import com.meritbank.model.CheckingAccount;
import com.meritbank.model.SavingsAccount;
import com.meritbank.repository.AccountHolderRepo;
import com.meritbank.repository.CDAccountRepo;
import com.meritbank.repository.CDOfferingRepo;
import com.meritbank.repository.CheckingAccountRepo;
import com.meritbank.repository.SavingsAccountRepo;


@Service
public class AccountsServiceImpl implements AccountsService {
	@Autowired
	private AccountHolderRepo accountHolderRepo;
	@Autowired
	private CheckingAccountRepo checkingAccountRepo;
	@Autowired
	private SavingsAccountRepo savingsAccountRepo;
	@Autowired
	private CDAccountRepo cdAccountRepo;
	@Autowired
	private CDOfferingRepo cdOfferingRepo;

//	Default Constructor
	public AccountsServiceImpl() {
	}

//	CheckingAccount Methods
	@Override
	public CheckingAccount addCheckingAccount(int id, CheckingAccount checkingAccount)
			throws ExceedsCombinedBalanceLimitException, NoSuchAccountException, InvalidArgumentException {
		if (accountHolderRepo.existsById(id)) {
			AccountHolder accountHolder = accountHolderRepo.getOne(id);
			if (accountHolder.getCombinedBalance() + checkingAccount.getBalance() > 250000) {
				throw new ExceedsCombinedBalanceLimitException("Combined balance cannot be greater than 250000");
			}
			if (checkingAccount.getBalance() < 0) {
				throw new InvalidArgumentException("Balance cannt be negative");
			}
			CheckingAccount checkAcc = new CheckingAccount(checkingAccount.getBalance());
			checkAcc.setAccountHolder(accountHolder);
			return checkingAccountRepo.save(checkAcc);
		}
		throw new NoSuchAccountException("No such account found");
	}

//	SavingsAccount Methods
	@Override
	public SavingsAccount addSavingsAccount(int id, SavingsAccount savingsAccount)
			throws ExceedsCombinedBalanceLimitException, NoSuchAccountException, InvalidArgumentException {
		if (accountHolderRepo.existsById(id)) {
			AccountHolder accountHolder = accountHolderRepo.getOne(id);
			if (accountHolder.getCombinedBalance() + savingsAccount.getBalance() > 250000) {
				throw new ExceedsCombinedBalanceLimitException("Combined balance cannot be greater than 250000");
			}
			if (savingsAccount.getBalance() < 0) {
				throw new InvalidArgumentException("Balance cannt be negative");
			}
			SavingsAccount savAcc = new SavingsAccount(savingsAccount.getBalance());
			savAcc.setAccountHolder(accountHolder);
			return savingsAccountRepo.save(savAcc);
		}
		throw new NoSuchAccountException("No such account found");
	}

//	CDAccount Methods
	@Override
	public CDAccount addCDAccount(int id, CDAccount cdAccount)
			throws ExceedsCombinedBalanceLimitException, NoSuchAccountException, InvalidArgumentException {
		if (accountHolderRepo.existsById(id)) {
			AccountHolder accountHolder = accountHolderRepo.getOne(id);
			if (accountHolder.getCombinedBalance() + cdAccount.getBalance() > 250000) {
				throw new ExceedsCombinedBalanceLimitException("Combined balance cannot be greater than 250000");
			}
			if (cdAccount.getBalance() < 0) {
				throw new InvalidArgumentException("Balance cannt be negative");
			}
			CDOffering offering = cdOfferingRepo.getOne(cdAccount.getCdOffering().getId());
			CDAccount cdAcc = new CDAccount(cdAccount.getBalance(), offering);
			cdAcc.setAccountHolder(accountHolder);
			return cdAccountRepo.save(cdAcc);
		}
		throw new NoSuchAccountException("No such account found");
	}

	@Override
	public CheckingAccount deleteCheckingAccount(int id, CheckingAccount checkingAccount)
			throws NoSuchAccountException {
		CheckingAccount checAcc = checkingAccountRepo.getById(null);
		return null;
	}

}
