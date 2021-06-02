package com.meritbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meritbank.model.SavingsAccount;

public interface SavingsAccountRepo extends JpaRepository<SavingsAccount, Integer> {

}