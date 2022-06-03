/*
 * Copyright (c) 2020. University of Applied Sciences and Arts Northwestern Switzerland FHNW.
 * All rights reserved.
 */

package ch.fhnw.GenZ.controller;

import ch.fhnw.GenZ.business.service.AgentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ch.fhnw.GenZ.data.domain.Agent;
import ch.fhnw.GenZ.data.domain.UserRole;

@Controller
public class UserController {

	@Autowired
	private AgentService agentService;

	// Autowiring logger to log, register and login data to txt file
	@Autowired
	private LogController logController;

	// Show different page views
	@GetMapping("/login")
	public String getLoginView() {
		logController.newlog.info("Login Page accessed!");
		return "user/login.html";
	}

	@GetMapping("/user/register")
	public String getRegisterView() {
		logController.newlog.info("Register Page accessed!");
		return "register.html";
	}

	@PostMapping("/user/register")
	public ResponseEntity<Void> postRegister(@RequestBody Agent agent) {
		agent.setRole(UserRole.USER);
		try {
			agentService.saveAgent(agent);
			logController.newlog.info("New registration of User: " + agent.getName());
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getMessage());
		}
		return ResponseEntity.ok().build();
	}

	@GetMapping("/admin/profile")
	public String getProfileView() {
		logController.newlog.info("Admin Profile page accessed by User: " + agentService.getCurrentAgent().getName());
		return "../admin/adminprofile.html";
	}

	@GetMapping("/profile")
	public @ResponseBody Agent getProfile() {
		logController.newlog.info("Profile page accessed by User: " + agentService.getCurrentAgent().getName());
		return agentService.getCurrentAgent();
	}

	@PostMapping("/profile")
	public ResponseEntity<Void> putProfile(@RequestBody Agent agent) {
		try {
			Agent agentExist = agentService.getCurrentAgent();
			agentExist.setName(agent.getName());
			agentExist.setEmail(agent.getEmail());
			agentExist.setPassword(agent.getPassword());
			agentService.saveAgent(agentExist);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getMessage());
		}
		return ResponseEntity.ok().build();
	}

	@GetMapping("/customer/profile")
	public String getCustomerProfileView() {
		logController.newlog.info("Customer Profile Page accessed by User: " + agentService.getCurrentAgent().getName());
		return "../customer/profile.html";
	}

	@GetMapping("/users")
	public String getUsersView() {
		return "admin/user.html";
	}

	@GetMapping("/customers")
	public String getCustomersView() {
		return "admin/customer.html";
	}

	@GetMapping("/products")
	public String getProductsView() {
		return "admin/product.html";
	}

	@GetMapping("/orders")
	public String getOrdersView() {
		return "admin/order.html";
	}

	@GetMapping("/productlist")
	public String getProductListView() {
		return "customer/productlist.html";
	}

	@GetMapping("/customerlist")
	public String getCustomerListView() {
		return "customer/customerlist.html";
	}

	@GetMapping("/cartlist")
	public String getCartListView() {
		return "customer/cartlist.html";
	}

	@GetMapping("/orderlist")
	public String getOrderListView() {
		return "customer/orderlist.html";
	}

	@RequestMapping(value = "/validate", method = { RequestMethod.GET, RequestMethod.HEAD })
	public ResponseEntity<Void> init() {
		return ResponseEntity.ok().build();
	}

}