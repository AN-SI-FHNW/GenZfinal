/*
 * Copyright (c) 2020. University of Applied Sciences and Arts Northwestern Switzerland FHNW.
 * All rights reserved.
 */

package ch.fhnw.GenZ.data.repository;

import ch.fhnw.GenZ.data.domain.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgentRepository extends JpaRepository<Agent, Long> {
	Agent findByEmail(String email);
	Agent findByEmailAndIdNot(String email, Long agentId);
}
