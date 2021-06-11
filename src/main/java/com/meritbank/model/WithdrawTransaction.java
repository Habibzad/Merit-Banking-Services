package com.meritbank.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class WithdrawTransaction extends Transaction {
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private int id;
	
	WithdrawTransaction(BankAccount targetAccount, double amount) {
		this.targetAccount = targetAccount;
		this.amount = amount;
		this.isProcessed = false;
	}

	@Override
	public void process() {
		this.targetAccount.withdraw(amount);
	}

}
