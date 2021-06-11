package com.meritbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meritbank.model.WithdrawTransaction;

public interface WithdrawTransactionRepo extends JpaRepository<WithdrawTransaction, Integer>{

}
