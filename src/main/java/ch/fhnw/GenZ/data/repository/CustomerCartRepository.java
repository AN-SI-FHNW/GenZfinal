/*
 * Copyright (c) 2020. University of Applied Sciences and Arts Northwestern Switzerland FHNW.
 * All rights reserved.
 */

package ch.fhnw.GenZ.data.repository;

import ch.fhnw.GenZ.data.domain.CustomerCart;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerCartRepository extends JpaRepository<CustomerCart, Long> {

	List<CustomerCart> findByAgentId(Long agentId);

	List<CustomerCart> findByAgentIdAndProductId(Long id, Long productId);

}
