package com.meritbank.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CDOffering {
	
	private static int nextID = 1;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private double interestRate;
	private int term;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cdOffering")
	@JsonIgnore
	private List<CDAccount> cdAccounts;

	public CDOffering(int term, double interestRate) {
		this.id = nextID++;
		this.term = term;
		this.interestRate = interestRate;
	}
}