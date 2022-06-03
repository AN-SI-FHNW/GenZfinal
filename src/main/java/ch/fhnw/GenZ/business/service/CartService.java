/*
 * Copyright (c) 2020. University of Applied Sciences and Arts Northwestern Switzerland FHNW.
 * All rights reserved.
 */

package ch.fhnw.GenZ.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import ch.fhnw.GenZ.data.domain.CustomerCart;
import ch.fhnw.GenZ.data.repository.CustomerCartRepository;

import javax.validation.Valid;
import java.util.List;

@Service
@Validated
public class CartService {

	@Autowired
	private CustomerCartRepository cartRepository;
	@Autowired
	private AgentService agentService;

	// Save Cart
	public CustomerCart saveCart(@Valid CustomerCart cart) 	{
		return cartRepository.save(cart);
	}

	// Find all carts from current Agent and corresponding id
	public List<CustomerCart> findAllByAgentId() {
		return cartRepository.findByAgentId(agentService.getCurrentAgent().getId());
	}

	// Find all carts from current AgentId and ProductID
	public List<CustomerCart> findByAgentIdAndProductId(Long productId) {
		return cartRepository.findByAgentIdAndProductId(agentService.getCurrentAgent().getId(), productId);
	}

	// Delete cart
	public void deleteCart(Long cartId) {
		cartRepository.deleteById(cartId);
	}

}
