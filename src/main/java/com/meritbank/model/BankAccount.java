package com.meritbank.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class BankAccount {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

//	Instance Variables	
	private double balance;
	private double interestRate;
	private LocalDateTime openingDate;

	@ManyToOne
	@JoinColumn(name = "account_holder_id")
	@JsonIgnore
	private AccountHolder accountHolder;

//	Parameterized Constructor
	public BankAccount(double balance) {
		this.balance = balance;
		this.interestRate = 0.01; // Interest Rate = 1%
		this.openingDate = LocalDateTime.now();
	}

	/**
	 * This method withdraws from the account if the withdraw amount is less than
	 * balance
	 * 
	 * @param amount
	 * @return boolean
	 */
	public boolean withdraw(double amount) {
		if (amount <= this.balance) {
			this.balance -= amount;
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * This method deposits in the account if the deposit amount is greater than
	 * zero
	 * 
	 * @param amount
	 * @return boolean
	 */
	public boolean deposit(double amount) {
		if (amount <= 0) {
			return false;
		} else {
			this.balance += amount;
			return true;
		}
	}
}
