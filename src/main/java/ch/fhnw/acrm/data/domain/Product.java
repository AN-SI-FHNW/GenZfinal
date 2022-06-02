/*
 * Copyright (c) 2020. University of Applied Sciences and Arts Northwestern Switzerland FHNW.
 * All rights reserved.
 */

package ch.fhnw.acrm.data.domain;

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
	private Double weight;
	private Integer maxNoOfProducts;
	private Double palletSize;
	private Double productPrice;
	private Integer availableQuantity;
	private String description;
	private String productCategory;

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

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Integer getMaxNoOfProducts() {
		return maxNoOfProducts;
	}

	public void setMaxNoOfProducts(Integer maxNoOfProducts) {
		this.maxNoOfProducts = maxNoOfProducts;
	}

	public Double getPalletSize() {
		return palletSize;
	}

	public void setPalletSize(Double palletSize) {
		this.palletSize = palletSize;
	}

	public Double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(Double productPrice) {
		this.productPrice = productPrice;
	}

	public Integer getAvailableQuantity() {
		return availableQuantity;
	}

	public void setAvailableQuantity(Integer availableQuantity) {
		this.availableQuantity = availableQuantity;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

}
