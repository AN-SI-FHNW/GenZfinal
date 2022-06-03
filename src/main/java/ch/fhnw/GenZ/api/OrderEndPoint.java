/*
 * Copyright (c) 2020. University of Applied Sciences and Arts Northwestern Switzerland FHNW.
 * All rights reserved.
 */

package ch.fhnw.GenZ.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import ch.fhnw.GenZ.business.service.OrderService;
import ch.fhnw.GenZ.data.domain.CustomerOrder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.validation.ConstraintViolationException;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/api") // /api/Orders
@Api(value = "/api", description = "Order End Point")
public class OrderEndPoint {
	@Autowired
	private OrderService orderService;

	// Add order/save order
	@ApiOperation(value = "To add an order", response = String.class)
	@PostMapping(path = "/order", consumes = "application/json", produces = "application/json")
	public ResponseEntity<CustomerOrder> postProduct(@RequestBody CustomerOrder order) {
		try {
			order = orderService.saveOrder(order);
		} catch (ConstraintViolationException e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,
					e.getConstraintViolations().iterator().next().getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
		}

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{orderId}")
				.buildAndExpand(order.getId()).toUri();

		return ResponseEntity.created(location).body(order);
	}

	// Get all orders
	@ApiOperation(value = "To get all orders", response = String.class)
	@GetMapping(path = "/order", produces = "application/json")
	public List<CustomerOrder> getOrders() {
		return orderService.findAllOrders();
	}

}