package com.meritbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meritbank.model.BankAccount;


public interface BankAccountRepo extends JpaRepository<BankAccount, Integer>{

}
