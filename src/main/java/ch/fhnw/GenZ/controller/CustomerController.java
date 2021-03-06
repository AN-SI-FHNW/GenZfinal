/*
 * Author: Kevin Pini		Date: 03.06.2022
 * Inspired by Documentation of Andreas Martin (Lecturer FHNW): https://github.com/DigiPR/acrm-sandbox
 */

package ch.fhnw.GenZ.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ch.fhnw.GenZ.data.domain.UserRole;

@Controller
@RequestMapping(path = "/customer")
public class CustomerController {

	// Show customer view / admin view, depending on authority
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

	// Show customer customerCreate page
	@GetMapping("/create")
	public String getCustomerCreateView() {
		return "../customer/customerCreate.html";
	}

	// Show customerEdit page
	@GetMapping("/edit")
	public String getCustomerEditView() {
		return "../customer/customerEdit.html";
	}
	
}
