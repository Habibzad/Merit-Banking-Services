package com.meritbank.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TransferTransaction extends Transaction {
	TransferTransaction(BankAccount sourceAccount, BankAccount targetAccount, double amount) {
		super();
		this.sourceAccount = sourceAccount;
		this.targetAccount = targetAccount;
		this.amount = amount;
		this.sourceAccount.withdraw(amount);
		this.targetAccount.deposit(amount);
	}
}