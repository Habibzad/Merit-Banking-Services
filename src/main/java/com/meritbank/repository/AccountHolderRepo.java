package com.meritbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.meritbank.model.AccountHolder;

@Repository
public interface AccountHolderRepo extends JpaRepository<AccountHolder, Integer> {
}
