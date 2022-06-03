/*
 * Copyright (c) 2020. University of Applied Sciences and Arts Northwestern Switzerland FHNW.
 * All rights reserved.
 */

package ch.fhnw.GenZ.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import ch.fhnw.GenZ.data.domain.Customer;
import ch.fhnw.GenZ.data.repository.CustomerRepository;

import javax.validation.Valid;
import java.util.List;

@Service
@Validated
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private AgentService agentService;

	// Edit Customer: check if agent already exist by mobile number and id
	public Customer editCustomer(@Valid Customer customer) throws Exception {
		if (customer.getId() == null) {
			if (customerRepository.findByMobile(customer.getMobile()) == null) {
				customer.setAgent(agentService.getCurrentAgent());
				return customerRepository.save(customer);
			}
			throw new Exception("Mobile number " + customer.getMobile() + " already assigned to a customer.");
		}
		if (customerRepository.findByMobileAndIdNot(customer.getMobile(), customer.getId()) == null) {
			if (customer.getAgent() == null) {
				customer.setAgent(agentService.getCurrentAgent());
			}
			return customerRepository.save(customer);
		}
		throw new Exception("Mobile number " + customer.getMobile() + " already assigned to a customer.");
	}

	// Delete customer by ID
	public void deleteCustomer(Long customerId)
	{
		customerRepository.deleteById(customerId);
	}

	// Find customer by ID
	public Customer findCustomerById(Long customerId) throws Exception {
		List<Customer> customerList = customerRepository.findByIdAndAgentId(customerId, agentService.getCurrentAgent().getId());
		if(customerList.isEmpty()){
			throw new Exception("No customer with ID "+customerId+" found.");
		}
		return customerList.get(0);
	}

	// Find all customers
	public List<Customer> findAllCustomers() {
		return customerRepository.findByAgentId(agentService.getCurrentAgent().getId());
	}

	// Find all customers for Admin (users)
	public List<Customer> findAllCustomersForAdmin() {
		return customerRepository.findAll();
	}
	
}
