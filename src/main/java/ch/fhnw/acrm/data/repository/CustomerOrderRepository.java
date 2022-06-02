/*
 * Copyright (c) 2020. University of Applied Sciences and Arts Northwestern Switzerland FHNW.
 * All rights reserved.
 */

package ch.fhnw.acrm.data.repository;

import ch.fhnw.acrm.data.domain.Agent;
import ch.fhnw.acrm.data.domain.CustomerOrder;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Long> {

	List<CustomerOrder> findByAgentId(Long id);

	List<CustomerOrder> findByAgent(Agent currentAgent);

}
