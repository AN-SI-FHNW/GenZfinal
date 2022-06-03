/*
 * Copyright (c) 2020. University of Applied Sciences and Arts Northwestern Switzerland FHNW.
 * All rights reserved.
 */

package ch.fhnw.GenZ.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import ch.fhnw.GenZ.data.domain.Product;
import ch.fhnw.GenZ.data.repository.ProductRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
@Validated
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	// Edit product
	public Product editProduct(@Valid Product product) throws Exception {
		if (product.getId() != null) {
			return productRepository.save(product);
		}
		throw new Exception("Product not found");
	}

	// Save product
	public Product saveProduct(@Valid Product product) {
		return productRepository.save(product);
	}

	// Delete product
	public void deleteProduct(Long productId) {
		productRepository.deleteById(productId);
	}

	// Find product by ID
	public Product findProductById(Long productId) throws Exception {
		Optional<Product> product = productRepository.findById(productId);
		if (!product.isPresent()) {
			throw new Exception("No product with ID " + productId + " found.");
		}
		return product.get();
	}

	// Find all products
	public List<Product> findAllProducts() {
		return productRepository.findAll();
	}

}
