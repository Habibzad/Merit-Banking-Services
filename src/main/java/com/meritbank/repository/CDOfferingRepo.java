package com.meritbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.meritbank.model.CDOffering;

@Repository
public interface CDOfferingRepo extends JpaRepository<CDOffering, Integer> {

}