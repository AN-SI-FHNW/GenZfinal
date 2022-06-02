/*
 * Copyright (c) 2020. University of Applied Sciences and Arts Northwestern Switzerland FHNW.
 * All rights reserved.
 */

package ch.fhnw.acrm.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ch.fhnw.acrm.business.service.CustomerService;
import ch.fhnw.acrm.data.domain.Customer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.validation.ConstraintViolationException;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/api") // /api/Products --> not just products but the right one
@Api(value = "/api", description = "Customer End Point")
public class CustomerEndpoint {
	@Autowired
	private CustomerService customerService;

	@ApiOperation(value = "To add a customer", response = String.class)
	@PostMapping(path = "/customer", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Customer> postCustomer(@RequestBody Customer customer) {
		try {
			customer = customerService.editCustomer(customer);
		} catch (ConstraintViolationException e) {
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,
					e.getConstraintViolations().iterator().next().getMessage());
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
		}

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{customerId}")
				.buildAndExpand(customer.getId()).toUri();

		return ResponseEntity.created(location).body(customer);
	}

	@ApiOperation(value = "To get all customers for agent", response = String.class)
	@GetMapping(path = "/customer", produces = "application/json")
	public List<Customer> getCustomers() {
		return customerService.findAllCustomers();
	}
	
	@ApiOperation(value = "To get all customers for administrator", response = String.class)
	@GetMapping(path = "/customerforadmin", produces = "application/json")
	public List<Customer> getAllCustomers() {
		return customerService.findAllCustomersForAdmin();
	}

	@ApiOperation(value = "To get a customer", response = String.class)
	@GetMapping(path = "/customer/{customerId}", produces = "application/json")
	public ResponseEntity<Customer> getCustomer(@PathVariable(value = "customerId") String customerId) {
		Customer customer = null;
		try {
			customer = customerService.findCustomerById(Long.parseLong(customerId));
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
		return ResponseEntity.ok(customer);
	}

	@ApiOperation(value = "To update a customer", response = String.class)
	@PutMapping(path = "/customer/{customerId}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Customer> putCustomer(@RequestBody Customer customer,
			@PathVariable(value = "customerId") String customerId) {
		try {
			customer.setId(Long.parseLong(customerId));
			customer = customerService.editCustomer(customer);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getMessage());
		}
		return ResponseEntity.accepted().body(customer);
	}

	@ApiOperation(value = "To delete a customer", response = String.class)
	@DeleteMapping(path = "/customer/{customerId}")
	public ResponseEntity<Void> deleteCustomer(@PathVariable(value = "customerId") String customerId) {
		try {
			customerService.deleteCustomer(Long.parseLong(customerId));
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getMessage());
		}
		return ResponseEntity.accepted().build();
	}
}