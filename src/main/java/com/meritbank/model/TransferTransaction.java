package com.meritbank.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TransferTransaction extends Transaction {
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private int id;
	
	TransferTransaction(BankAccount sourceAccount, BankAccount targetAccount, double amount) {
		this.sourceAccount = sourceAccount;
		this.targetAccount = targetAccount;
		this.amount = amount;
		this.isProcessed = false;
	}

	@Override
	public void process() {
		this.sourceAccount.withdraw(amount);
		this.targetAccount.deposit(amount);
	}
}