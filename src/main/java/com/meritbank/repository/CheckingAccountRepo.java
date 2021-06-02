package com.meritbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.meritbank.model.CheckingAccount;

@Repository
public interface CheckingAccountRepo extends JpaRepository<CheckingAccount, Integer> {

}