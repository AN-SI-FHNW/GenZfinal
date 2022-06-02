/*
 * Copyright (c) 2020. University of Applied Sciences and Arts Northwestern Switzerland FHNW.
 * All rights reserved.
 */

package ch.fhnw.GenZ.controller;

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
import org.springframework.web.server.NotAcceptableStatusException;
import org.springframework.web.server.ResponseStatusException;

import ch.fhnw.GenZ.business.service.AgentService;
import ch.fhnw.GenZ.business.service.CartService;
import ch.fhnw.GenZ.business.service.CustomerService;
import ch.fhnw.GenZ.business.service.DistanceService;
import ch.fhnw.GenZ.business.service.OrderService;
import ch.fhnw.GenZ.business.service.ProductService;
import ch.fhnw.GenZ.business.service.TransportCostService;
import ch.fhnw.GenZ.data.domain.Customer;
import ch.fhnw.GenZ.data.domain.CustomerCart;
import ch.fhnw.GenZ.data.domain.CustomerOrder;
import ch.fhnw.GenZ.data.domain.Distance;
import ch.fhnw.GenZ.data.domain.Product;
import ch.fhnw.GenZ.data.domain.TransportCost;

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
			String fromCity = "Zürich"; //we have only 1 central warehouse in ZH
			String toCity = order.getCustomer().getCity();
			Distance distance = distanceService.findByToCity(fromCity, toCity);
			Integer kilometers = distance.getKilometers();
			Integer pal = new Double((order.getProduct().getPalletSize() * order.getOrderQuantity())).intValue();
			Double shippingCost = 0.0;
			for (TransportCost transportCost : transportCostService.getAllTransportCost()) {
				int transportKm = transportCost.getKm();
				switch (transportKm) {
					case 30:
						if(kilometers <= transportKm&& pal == transportCost.getPal()) {
							shippingCost = transportCost.getCost();
						}
						break;
					case 60:
						if(kilometers <= transportKm && kilometers >30 && pal == transportCost.getPal()) {
							shippingCost = transportCost.getCost();
						}
						break;
					case 90:
						if(kilometers <= transportKm && kilometers >60 && pal == transportCost.getPal()) {
							shippingCost = transportCost.getCost();
						}
						break;
					case 120:
						if(kilometers <= transportKm && kilometers >90 && pal == transportCost.getPal()) {
							shippingCost = transportCost.getCost();
						}
						break;
					case 150:
						if(kilometers <= transportKm && kilometers >120 && pal == transportCost.getPal()) {
							shippingCost = transportCost.getCost();
						}
						break;
					case 180:
						if(kilometers <= transportKm && kilometers >150 && pal == transportCost.getPal()) {
							shippingCost = transportCost.getCost();
						}
						break;
					case 210:
						if(kilometers <= transportKm && kilometers >180 && pal == transportCost.getPal()) {
							shippingCost = transportCost.getCost();
						}
						break;
					case 240:
						if(kilometers <= transportKm && kilometers >210 && pal == transportCost.getPal()) {
							shippingCost = transportCost.getCost();
						}
						break;
					case 270:
						if(kilometers <= transportKm && kilometers >240 && pal == transportCost.getPal()) {
							shippingCost = transportCost.getCost();
						}
						break;
					case 300:
						if(kilometers <= transportKm && kilometers >270 && pal == transportCost.getPal()) {
							shippingCost = transportCost.getCost();
						}
						break;
					case 330:
						if(kilometers <= transportKm && kilometers >300 && pal == transportCost.getPal()) {
							shippingCost = transportCost.getCost();
						}
						break;
					case 360:
						if(kilometers <= transportKm && kilometers >330 && pal == transportCost.getPal()) {
							shippingCost = transportCost.getCost();
						}
						break;
				}
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
