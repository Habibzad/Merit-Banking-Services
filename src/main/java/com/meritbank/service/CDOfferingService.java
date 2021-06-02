package com.meritbank.service;

import java.util.List;

import com.meritbank.exceptions.InvalidArgumentException;
import com.meritbank.model.CDOffering;


public interface CDOfferingService {
	
	public CDOffering addCDOffering(CDOffering cdOffering) throws InvalidArgumentException;

	public List<CDOffering> getCDOfferings();
	
}