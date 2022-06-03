/*
 * Author: Kevin Pini		Date: 03.06.2022
 * Inspired by Documentation of Andreas Martin (Lecturer FHNW): https://github.com/DigiPR/acrm-sandbox
 */

package ch.fhnw.GenZ.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ch.fhnw.GenZ.business.service.AgentService;
import ch.fhnw.GenZ.data.domain.Agent;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;

@RestController
@RequestMapping(path = "/api")
@Api(value = "/api", description = "User End Point")

public class AgentEndPoint {
	@Autowired
	private AgentService agentService;

	// API produces all users
	@ApiOperation(value = "To get all users", response = String.class)
	@GetMapping(path = "/user", produces = "application/json")
	public List<Agent> getAllUsers() {
		return agentService.getAllAgents();
	}

	// Api deletes user
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