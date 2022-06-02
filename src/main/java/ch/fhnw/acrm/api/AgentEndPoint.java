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

import ch.fhnw.acrm.business.service.AgentService;
import ch.fhnw.acrm.data.domain.Agent;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

@RestController
@RequestMapping(path = "/api") // /api/Users
@Api(value = "/api", description = "User End Point")
public class AgentEndPoint {
	@Autowired
	private AgentService agentService;

	@ApiOperation(value = "To get all users", response = String.class)
	@GetMapping(path = "/user", produces = "application/json")
	public List<Agent> getAllUsers() {
		return agentService.getAllAgents();
	}
	
	@ApiOperation(value = "To delete a user", response = String.class)
	@DeleteMapping(path = "/user/{userId}")
	public ResponseEntity<Void> deleteUser(@PathVariable(value = "userId") String userId) {
		try {
			agentService.deleteUser(Long.parseLong(userId));
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getMessage());
		}
		return ResponseEntity.accepted().build();
	}

}