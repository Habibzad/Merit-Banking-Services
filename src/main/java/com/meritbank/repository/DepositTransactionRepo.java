package com.meritbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meritbank.model.DepositTransaction;

public interface DepositTransactionRepo extends JpaRepository<DepositTransaction, Integer>{

}
