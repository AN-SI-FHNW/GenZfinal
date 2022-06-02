/*
 * Copyright (c) 2020. University of Applied Sciences and Arts Northwestern Switzerland FHNW.
 * All rights reserved.
 */

package ch.fhnw.acrm.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import ch.fhnw.acrm.data.domain.Distance;
import ch.fhnw.acrm.data.repository.DistanceRepository;

@Service
@Validated
public class DistanceService {

	@Autowired
	private DistanceRepository distanceRepository;

	public void saveDistance(Distance distance) throws Exception {
		distanceRepository.save(distance);
	}

	public void deleteDistance() throws Exception {
		distanceRepository.deleteAll();
	}

	public Distance findByToCity(String fromCity, String toCity) throws Exception {
		return distanceRepository.findByFromCityAndToCity(fromCity, toCity);
	}

}
