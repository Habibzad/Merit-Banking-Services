package com.meritbank.model;

import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class CheckingAccount extends BankAccount {
		
	public CheckingAccount(double balance) {
		super(balance);
	}

}
