/*
 * Author: Carla Kaufmann		Date: 03.06.2022
 * Inspired by Documentation of Andreas Martin (Lecturer FHNW): https://github.com/DigiPR/acrm-sandbox
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
