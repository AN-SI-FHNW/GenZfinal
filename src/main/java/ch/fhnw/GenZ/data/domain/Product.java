/*
 * Copyright (c) 2020. University of Applied Sciences and Arts Northwestern Switzerland FHNW.
 * All rights reserved.
 */

package ch.fhnw.GenZ.data.domain;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
public class Product {

	@Id
	@GeneratedValue
	private Long id;

	private Long supplierId;
	@NotEmpty(message = "Please provide a name.")
	private String name;
	private Integer maxNoOfProducts;
	private Double minNrOfPalletSpaces;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getMaxNoOfProducts() {
		return maxNoOfProducts;
	}

	public void setMaxNoOfProducts(Integer maxNoOfProducts) {
		this.maxNoOfProducts = maxNoOfProducts;
	}

	public Double getMinNrOfPalletSpaces() {
		return minNrOfPalletSpaces;
	}

	public void setMinNrOfPalletSpaces(Double palletSize) {
		this.minNrOfPalletSpaces = palletSize;
	}
}
