/*
 * Copyright (c) 2020. University of Applied Sciences and Arts Northwestern Switzerland FHNW.
 * All rights reserved.
 */

package ch.fhnw.acrm.business.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import ch.fhnw.acrm.data.domain.TransportCost;
import ch.fhnw.acrm.data.repository.TransportCostRepository;

@Service
@Validated
public class TransportCostService {

	@Autowired
	private TransportCostRepository transportCostRepository;

	public void saveTransportCost(TransportCost transportCost) throws Exception {
		transportCostRepository.save(transportCost);
	}

	public void deleteTransportCost() throws Exception {
		transportCostRepository.deleteAll();
	}

	public List<TransportCost> getAllTransportCost() throws Exception {
		return transportCostRepository.findAll();
	}
}
