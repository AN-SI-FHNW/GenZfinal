/*
 * Copyright (c) 2020. University of Applied Sciences and Arts Northwestern Switzerland FHNW.
 * All rights reserved.
 */

package ch.fhnw.GenZ.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import ch.fhnw.GenZ.data.domain.CustomerOrder;
import ch.fhnw.GenZ.data.repository.CustomerOrderRepository;

import javax.validation.Valid;
import java.util.List;

@Service
@Validated
public class OrderService {

	@Autowired
	private CustomerOrderRepository orderRepository;

	// Save order to repository
	public CustomerOrder saveOrder(@Valid CustomerOrder order) {
		return orderRepository.save(order);
	}

	// Find all orders from repository
	public List<CustomerOrder> findAllOrders() {
		return orderRepository.findAll();
	}

}
