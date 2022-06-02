/*
 * Copyright (c) 2020. University of Applied Sciences and Arts Northwestern Switzerland FHNW.
 * All rights reserved.
 */

package ch.fhnw.GenZ.data.domain;

import java.util.Date;

import javax.persistence.*;

@Entity
public class CustomerOrderItem {

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	private Agent agent;
	
	@ManyToOne
	private Product product;
	
	@ManyToOne
	private Customer customer;
	private Date orderDate;
	private Integer orderQuantity;
	private Double shippingCost;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Integer getOrderQuantity() {
		return orderQuantity;
	}

	public void setOrderQuantity(Integer orderQuantity) {
		this.orderQuantity = orderQuantity;
	}

	public Double getShippingCost() {
		return shippingCost;
	}

	public void setShippingCost(Double shippingCost) {
		this.shippingCost = shippingCost;
	}

}
