package com.meritbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.meritbank.model.AccountHoldersContactDetails;

@Repository
public interface AccountHoldersContactDetailsRepo extends JpaRepository<AccountHoldersContactDetails, Integer> {

}
