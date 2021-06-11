package com.meritbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meritbank.model.TransferTransaction;

public interface TransferTransactionRepo extends JpaRepository<TransferTransaction, Integer>{

}
