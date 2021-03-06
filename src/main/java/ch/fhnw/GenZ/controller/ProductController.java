/*
 * Author: Moana Kleiner		Date: 03.06.2022
 * Inspired by Documentation of Andreas Martin (Lecturer FHNW): https://github.com/DigiPR/acrm-sandbox
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

	// Autowiring different services
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

	// Get different pages
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

	// Create customer cart, add products, add agent, save cart, lastly return productlist.html
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

	// Order process
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
			// Warehouse location is kept as fromCanton which is a constant
			String fromCanton = "Z??rich"; //we have only 1 central warehouse in ZH
			String toCanton = order.getCustomer().getCanton();

			// Create variables
			Double maxNoOfProducts = Double.valueOf(order.getProduct().getMaxNoOfProducts());
			Double minNrOfPalletSpaces = order.getProduct().getMinNrOfPalletSpaces();
			Double orderQuantity = Double.valueOf(order.getOrderQuantity());

			// Get km category from actual distance
			Distance distance = distanceService.findByToCanton(fromCanton, toCanton);
			Integer kilometers = distance.getKilometers();
			Integer km = 0;
			if (kilometers <= 30) {km = 30;}
			else if (kilometers <= 60) {km = 60;}
			else if (kilometers <= 90) {km = 90;}
			else if (kilometers <= 120) {km = 120;}
			else if (kilometers <= 150) {km = 150;}
			else if (kilometers <= 180) {km = 180;}
			else if (kilometers <= 210) {km = 210;}
			else if (kilometers <= 240) {km = 240;}
			else if (kilometers <= 270) {km = 270;}
			else if (kilometers <= 300) {km = 300;}
			else if (kilometers <= 330) {km = 330;}
			else {km = 360;}

			// Get rounded up number of pallets (pal)
			Double roundedRatio = Math.ceil(orderQuantity / maxNoOfProducts);
			Double pal = Math.ceil(roundedRatio * minNrOfPalletSpaces);

			// Loop all items of transportCost to find correct item with matching km and pal
			Double shippingCost = 0.0;
			for (TransportCost transportCost : transportCostService.getAllTransportCost()) {
				if (transportCost.getKm().equals(km) && transportCost.getPal().equals(pal)) {
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

	// Get orderview
	@GetMapping("/buy")
	public String getProductOrderView() {
		return "../customer/order.html";
	}
}
