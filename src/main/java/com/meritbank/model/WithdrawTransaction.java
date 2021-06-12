package com.meritbank.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class WithdrawTransaction extends Transaction {

	WithdrawTransaction(BankAccount targetAccount, double amount) {
		super();
		this.targetAccount = targetAccount;
		this.amount = amount;
		this.targetAccount.withdraw(amount);
	}
}
