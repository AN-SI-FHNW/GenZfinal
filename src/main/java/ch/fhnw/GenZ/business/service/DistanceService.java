/*
 * Author: Moana Kleiner		Date: 03.06.2022
 * Inspired by Documentation of Andreas Martin (Lecturer FHNW): https://github.com/DigiPR/acrm-sandbox
 */

package ch.fhnw.GenZ.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import ch.fhnw.GenZ.data.domain.Distance;
import ch.fhnw.GenZ.data.repository.DistanceRepository;

@Service
@Validated
public class DistanceService {

	@Autowired
	private DistanceRepository distanceRepository;

	// Save distance from zürich to canton
	public void saveDistance(Distance distance) throws Exception {
		distanceRepository.save(distance);
	}

	// Delete distance
	public void deleteDistance() throws Exception {
		distanceRepository.deleteAll();
	}

	// Find element with distance data from canton to canton
	public Distance findByToCanton(String fromCanton, String toCanton) throws Exception {
		return distanceRepository.findByFromCantonAndToCanton(fromCanton, toCanton);
	}

}
