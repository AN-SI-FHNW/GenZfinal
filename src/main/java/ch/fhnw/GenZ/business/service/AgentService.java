/*
 * Author: Kevin Pini		Date: 03.06.2022
 * Inspired by Documentation of Andreas Martin (Lecturer FHNW): https://github.com/DigiPR/acrm-sandbox
 */

package ch.fhnw.GenZ.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import ch.fhnw.GenZ.data.domain.Agent;
import ch.fhnw.GenZ.data.domain.Customer;
import ch.fhnw.GenZ.data.domain.CustomerOrder;
import ch.fhnw.GenZ.data.repository.AgentRepository;
import ch.fhnw.GenZ.data.repository.CustomerOrderRepository;
import ch.fhnw.GenZ.data.repository.CustomerRepository;
import java.util.List;
import javax.validation.Valid;
import javax.validation.Validator;

@Service
@Validated
public class AgentService {

	// Autowiring to JPA repositories, password validator and encoder
	@Autowired
	private AgentRepository agentRepository;
	@Autowired
	private CustomerOrderRepository customerOrderRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	Validator validator;
	@Autowired
	private PasswordEncoder passwordEncoder;

	// Save Agent and check if ID or email already exists and set password with passwordEncoder
	public void saveAgent(@Valid Agent agent) throws Exception {
		if (agent.getId() == null) {
			if (agentRepository.findByEmail(agent.getEmail()) != null) {
				throw new Exception("Email address " + agent.getEmail() + " already assigned another agent.");
			}
		} else if (agentRepository.findByEmailAndIdNot(agent.getEmail(), agent.getId()) != null) {
			throw new Exception("Email address " + agent.getEmail() + " already assigned another agent.");
		}
		agent.setPassword(passwordEncoder.encode(agent.getPassword()));
		agentRepository.save(agent);
	}

	// Get current Agent and show username
	public Agent getCurrentAgent() {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return agentRepository.findByEmail(user.getUsername());
	}

	// Get all agents
	public List<Agent> getAllAgents() {
		return agentRepository.findAll();
	}

	// Delete user by user ID
	public void deleteUser(Long userId) {
		for (CustomerOrder customerOrder : customerOrderRepository.findByAgentId(userId)) {
			customerOrderRepository.deleteById(customerOrder.getId());
		}
		for (Customer customer : customerRepository.findByAgentId(userId)) {
			customerRepository.deleteById(customer.getId());
		}
		agentRepository.deleteById(userId);
	}
}
