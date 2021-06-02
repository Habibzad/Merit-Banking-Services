package com.meritbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meritbank.model.CDAccount;

public interface CDAccountRepo extends JpaRepository<CDAccount, Integer> {

}
