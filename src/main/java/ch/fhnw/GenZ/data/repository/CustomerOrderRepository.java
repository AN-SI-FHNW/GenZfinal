/*
 * Author: Carla Kaufmann	Date: 03.06.2022
 * Inspired by Documentation of Andreas Martin (Lecturer FHNW): https://github.com/DigiPR/acrm-sandbox
 */

package ch.fhnw.GenZ.data.repository;

import ch.fhnw.GenZ.data.domain.Agent;
import ch.fhnw.GenZ.data.domain.CustomerOrder;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Long> {

	List<CustomerOrder> findByAgentId(Long id);

	List<CustomerOrder> findByAgent(Agent currentAgent);

}
