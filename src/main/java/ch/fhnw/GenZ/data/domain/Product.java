/*
 * Author: Andrea Alec Simonek		Date: 03.06.2022
 * Inspired by Documentation of Andreas Martin (Lecturer FHNW): https://github.com/DigiPR/acrm-sandbox
 */

package ch.fhnw.GenZ.data.domain;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
public class Product {

	@Id
	@GeneratedValue
	private Long id;

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

	public void setMinNrOfPalletSpaces(Double minNrOfPalletSpaces) {
		this.minNrOfPalletSpaces = minNrOfPalletSpaces;
	}
}
