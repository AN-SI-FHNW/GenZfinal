/*
 * Author: Kevin Pini		Date: 03.06.2022
 * Inspired by Documentation of Andreas Martin (Lecturer FHNW): https://github.com/DigiPR/acrm-sandbox
 */

package ch.fhnw.GenZ.data.repository;

import ch.fhnw.GenZ.data.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	Customer findByMobile(String mobile);
	Customer findByMobileAndIdNot(String mobile, Long agentId);
	List<Customer> findByAgentId(Long agentId);
	List<Customer> findByIdAndAgentId(Long customerId, Long agentId);
}