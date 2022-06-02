/*
 * Copyright (c) 2020. University of Applied Sciences and Arts Northwestern Switzerland FHNW.
 * All rights reserved.
 */

package ch.fhnw.acrm.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ch.fhnw.acrm.data.domain.UserRole;

@Controller
@RequestMapping(path = "/customer")
public class CustomerController {

	@GetMapping
	public String getCustomerView(Authentication authentication) {
		String role = null;
		for (GrantedAuthority grantedAuthority : authentication.getAuthorities()) {
			role = grantedAuthority.getAuthority();
		}
		if (role.equals("ROLE_" + UserRole.ADMINISTRATOR.toString())) {
			return "admin/admin.html";
		}
		return "customer/landing.html";
	}

	@GetMapping("/create")
	public String getCustomerCreateView() {
		return "../customer/customerCreate.html";
	}

	@GetMapping("/edit")
	public String getCustomerEditView() {
		return "../customer/customerEdit.html";
	}
	
}
