/*
 * Copyright (c) 2020. University of Applied Sciences and Arts Northwestern Switzerland FHNW.
 * All rights reserved.
 */

package ch.fhnw.acrm.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import ch.fhnw.acrm.business.service.AgentService;
import ch.fhnw.acrm.business.service.CartService;
import ch.fhnw.acrm.business.service.CustomerService;
import ch.fhnw.acrm.business.service.DistanceService;
import ch.fhnw.acrm.business.service.OrderService;
import ch.fhnw.acrm.business.service.ProductService;
import ch.fhnw.acrm.business.service.TransportCostService;
import ch.fhnw.acrm.data.domain.Customer;
import ch.fhnw.acrm.data.domain.CustomerCart;
import ch.fhnw.acrm.data.domain.CustomerOrder;
import ch.fhnw.acrm.data.domain.Distance;
import ch.fhnw.acrm.data.domain.Product;
import ch.fhnw.acrm.data.domain.TransportCost;

@Controller
@RequestMapping(path = "/product")
public class ProductController {

	@Autowired
	private CartService cartService;
	@Autowired
	private AgentService agentService;
	@Autowired
	private ProductService productService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private TransportCostService transportCostService;
	@Autowired
	private DistanceService distanceService;

	@GetMapping
	public String getProductView() {
		return "admin/product.html";
	}

	@GetMapping("/create")
	public String getProductCreateView() {
		return "../admin/productCreate.html";
	}

	@GetMapping("/edit")
	public String getProductEditView() {
		return "../admin/productEdit.html";
	}

	@GetMapping("/cart")
	public String cart(@RequestParam Long id) {
		try {
			if (cartService.findByAgentIdAndProductId(id).size() == 0) {
				CustomerCart customerCart = new CustomerCart();
				customerCart.setProduct(productService.findProductById(id));
				customerCart.setAgent(agentService.getCurrentAgent());
				cartService.saveCart(customerCart);
			}
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getMessage());
		}
		return "../customer/productlist.html";
	}

	@PostMapping("/order")
	public ResponseEntity<Void> order(@RequestBody CustomerOrder order) {
		try {
			Product product = productService.findProductById(order.getProductId());
			Customer customer = customerService.findCustomerById(order.getCustomerId());
			order.setCustomer(customer);
			order.setProduct(product);
			order.setAgent(agentService.getCurrentAgent());
			order.setOrderDate(new Date());

			// shipping cost calculation start
			// Warehouse location is kept as fromCity which is a constant
			String fromCity = "Zürich";
			String toCity = order.getCustomer().getCity();
			Distance distance = distanceService.findByToCity(fromCity, toCity);
			Integer kilometers = distance.getKilometers();
			Integer pal = new Double((order.getProduct().getPalletSize() * order.getOrderQuantity())).intValue();
			Double shippingCost = 0.0;
			for (TransportCost transportCost : transportCostService.getAllTransportCost()) {
				if (kilometers <= transportCost.getKm() && pal == transportCost.getPal()) {
					shippingCost = transportCost.getCost();
					break;
				}
			}
			order.setShippingCost(shippingCost);
			// shipping cost calculation end
			
			orderService.saveOrder(order);
			cartService.deleteCart(cartService.findByAgentIdAndProductId(order.getProductId()).get(0).getId());
		} catch (Exception e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getMessage());
		}
		return ResponseEntity.ok().build();
	}

	@GetMapping("/buy")
	public String getProductOrderView() {
		return "../customer/order.html";
	}
}
