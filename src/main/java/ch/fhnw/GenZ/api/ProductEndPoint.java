/*
 * Author: Andrea Alec Simonek		Date: 03.06.2022
 * Inspired by Documentation of Andreas Martin (Lecturer FHNW): https://github.com/DigiPR/acrm-sandbox
 */

package ch.fhnw.GenZ.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ch.fhnw.GenZ.business.service.ProductService;
import ch.fhnw.GenZ.data.domain.Product;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.validation.ConstraintViolationException;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/api")
@Api(value = "/api", description = "Product End Point")
public class ProductEndPoint {
	@Autowired
	private ProductService productService;

	// Add product / save product
	@ApiOperation(value = "To add a product", response = String.class)
	@PostMapping(path = "/product", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Product> postProduct(@RequestBody Product product) {
		try {
			product = productService.saveProduct(product);
		} catch (ConstraintViolationException e) {
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,
					e.getConstraintViolations().iterator().next().getMessage());
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
		}

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{productId}")
				.buildAndExpand(product.getId()).toUri();

		return ResponseEntity.created(location).body(product);
	}

	// Get all products
	@ApiOperation(value = "To get all products", response = String.class)
	@GetMapping(path = "/product", produces = "application/json")
	public List<Product> getProducts() {
		return productService.findAllProducts();
	}

	// Get one product
	@ApiOperation(value = "To get a product", response = String.class)
	@GetMapping(path = "/product/{productId}", produces = "application/json")
	public ResponseEntity<Product> getProduct(@PathVariable(value = "productId") String productId) {
		Product product = null;
		try {
			product = productService.findProductById(Long.parseLong(productId));
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
		return ResponseEntity.ok(product);
	}

	// Update product
	@ApiOperation(value = "To update a product", response = String.class)
	@PutMapping(path = "/product/{productId}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Product> putProduct(@RequestBody Product product,
			@PathVariable(value = "productId") String productId) {
		try {
			product.setId(Long.parseLong(productId));
			product = productService.editProduct(product);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getMessage());
		}
		return ResponseEntity.accepted().body(product);
	}

	// Delete product
	@ApiOperation(value = "To delete a product", response = String.class)
	@DeleteMapping(path = "/product/{productId}")
	public ResponseEntity<Void> deleteProduct(@PathVariable(value = "productId") String productId) {
		try {
			productService.deleteProduct(Long.parseLong(productId));
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getMessage());
		}
		return ResponseEntity.accepted().build();
	}
}