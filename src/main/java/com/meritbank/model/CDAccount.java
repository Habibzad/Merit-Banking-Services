package com.meritbank.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CDAccount extends BankAccount {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cdoffering_id")
	private CDOffering cdOffering;
	
	public CDAccount(double balance, CDOffering offering) {
		super(balance);
		this.cdOffering = offering;
	}
}
