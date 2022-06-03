/*
 * Author: Moana Kleiner	Date: 03.06.2022
 * Inspired by Documentation of Andreas Martin (Lecturer FHNW): https://github.com/DigiPR/acrm-sandbox
 */

package ch.fhnw.GenZ.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ch.fhnw.GenZ.data.domain.Distance;

@Repository
public interface DistanceRepository extends JpaRepository<Distance, Long> {

	Distance findByFromCantonAndToCanton(String fromCanton, String toCanton);

}
