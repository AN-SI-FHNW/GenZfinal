/*
 * Author: Moana		Date: 03.06.2022
 * Inspired by Documentation of Andreas Martin (Lecturer FHNW): https://github.com/DigiPR/acrm-sandbox
 */

package ch.fhnw.GenZ.business.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import ch.fhnw.GenZ.data.domain.TransportCost;
import ch.fhnw.GenZ.data.repository.TransportCostRepository;

@Service
@Validated
public class TransportCostService {

	@Autowired
	private TransportCostRepository transportCostRepository;

	// Save transport cost to repository
	public void saveTransportCost(TransportCost transportCost) throws Exception {
		transportCostRepository.save(transportCost);
	}

 	// delete transport cost from repository
	public void deleteTransportCost() throws Exception {
		transportCostRepository.deleteAll();
	}

	// Get all transport cost from repository
	public List<TransportCost> getAllTransportCost() throws Exception {
		return transportCostRepository.findAll();
	}
}
