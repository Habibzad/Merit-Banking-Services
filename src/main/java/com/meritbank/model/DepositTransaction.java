package com.meritbank.model;

import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
public class DepositTransaction extends Transaction {

	double amount;

	DepositTransaction(BankAccount targetAccount, double amount) {
		super();
		this.targetAccount = targetAccount;
		this.amount = amount;
		this.targetAccount.deposit(amount);
	}
}
