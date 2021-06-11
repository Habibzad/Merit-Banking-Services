package com.meritbank.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class DepositTransaction extends Transaction {
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private int id;
	// Instance variables
	double amount;
	boolean isProcessed;

	// Constructor
	DepositTransaction(BankAccount targetAccount, double amount) {
		super();
		this.targetAccount = targetAccount;
		this.amount = amount;
		this.isProcessed = false;
	}

	@Override
	public void process() {
		this.targetAccount.deposit(amount);
		isProcessed = true;
	}

}
