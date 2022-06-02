/*
 * Copyright (c) 2020. University of Applied Sciences and Arts Northwestern Switzerland FHNW.
 * All rights reserved.
 */

package ch.fhnw.acrm.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import ch.fhnw.acrm.data.domain.Agent;
import ch.fhnw.acrm.data.domain.Customer;
import ch.fhnw.acrm.data.domain.CustomerOrder;
import ch.fhnw.acrm.data.repository.AgentRepository;
import ch.fhnw.acrm.data.repository.CustomerOrderRepository;
import ch.fhnw.acrm.data.repository.CustomerRepository;

import java.util.List;

import javax.validation.Valid;
import javax.validation.Validator;

@Service
@Validated
public class AgentService {

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

	public Agent getCurrentAgent() {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return agentRepository.findByEmail(user.getUsername());
	}

	public List<Agent> getAllAgents() {
		return agentRepository.findAll();
	}

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
