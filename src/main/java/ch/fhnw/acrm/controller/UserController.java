/*
 * Copyright (c) 2020. University of Applied Sciences and Arts Northwestern Switzerland FHNW.
 * All rights reserved.
 */

package ch.fhnw.acrm.controller;

import ch.fhnw.acrm.business.service.AgentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ch.fhnw.acrm.data.domain.Agent;
import ch.fhnw.acrm.data.domain.UserRole;

@Controller
public class UserController {

	@Autowired
	private AgentService agentService;

	@GetMapping("/login")
	public String getLoginView() {
		return "user/login.html";
	}

	@GetMapping("/aboutus")
	public String getAboutUsView() {
		return "aboutus.html";
	}

	@GetMapping("/user/register")
	public String getRegisterView() {
		return "register.html";
	}

	@PostMapping("/user/register")
	public ResponseEntity<Void> postRegister(@RequestBody Agent agent) {
		agent.setRole(UserRole.USER);
		try {
			agentService.saveAgent(agent);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getMessage());
		}
		return ResponseEntity.ok().build();
	}

	@GetMapping("/admin/profile")
	public String getProfileView() {
		return "../admin/adminprofile.html";
	}

	@GetMapping("/profile")
	public @ResponseBody Agent getProfile() {
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