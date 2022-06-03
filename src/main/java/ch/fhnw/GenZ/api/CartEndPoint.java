/*
 * Author: Carla Kaufmann		Date: 03.06.2022
 * Inspired by Documentation of Andreas Martin (Lecturer FHNW): https://github.com/DigiPR/acrm-sandbox
 */

package ch.fhnw.GenZ.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ch.fhnw.GenZ.business.service.CartService;
import ch.fhnw.GenZ.data.domain.CustomerCart;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;

@RestController
@RequestMapping(path = "/api") // /api/Cart
@Api(value = "/api", description = "Cart End Point")
public class CartEndPoint {
	@Autowired
	private CartService cartService;

	// API produces cart
	@ApiOperation(value = "To get the cart", response = String.class)
	@GetMapping(path = "/cart", produces = "application/json")
	public List<CustomerCart> getCart() {
		return cartService.findAllByAgentId();
	}

	//API deletes cart
	@ApiOperation(value = "To delete the cart", response = String.class)
	@DeleteMapping(path = "/cart/{cartId}")
	public ResponseEntity<Void> deleteCart(@PathVariable(value = "cartId") String cartId) {
		try {
			cartService.deleteCart(Long.parseLong(cartId));
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getMessage());
		}
		return ResponseEntity.accepted().build();
	}

}