/*
 * Copyright (c) 2020. University of Applied Sciences and Arts Northwestern Switzerland FHNW.
 * All rights reserved.
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

	// Find element with distance data from city to city
	public Distance findByToCity(String fromCity, String toCity) throws Exception {
		return distanceRepository.findByFromCityAndToCity(fromCity, toCity);
	}

}
