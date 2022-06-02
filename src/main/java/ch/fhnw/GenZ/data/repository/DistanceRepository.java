/*
 * Copyright (c) 2020. University of Applied Sciences and Arts Northwestern Switzerland FHNW.
 * All rights reserved.
 */

package ch.fhnw.GenZ.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ch.fhnw.GenZ.data.domain.Distance;

@Repository
public interface DistanceRepository extends JpaRepository<Distance, Long> {

	Distance findByFromCityAndToCity(String fromCity, String toCity);

}
