package com.datadog.springJmsExample.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.datadog.springJmsExample.domain.Order;
import com.datadog.springJmsExample.services.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderResource {
	
	@Autowired
	OrderService orderService;

	@PostMapping("/new")
	public ResponseEntity<Order> createOrder(@RequestParam Long userId, @RequestBody Order order) {
		Order newOrder = orderService.createOrUpdateOrder(userId, order);
		return new ResponseEntity<Order>(newOrder, HttpStatus.OK);
	}
	
	@GetMapping("")
	public ResponseEntity<Iterable<Order>> getAllOrders() {
		Iterable<Order> orders = orderService.getAllOrders();
		return new ResponseEntity<Iterable<Order>>(orders, HttpStatus.OK);
	}
	
	@GetMapping("/byUser")
	public ResponseEntity<Iterable<Order>> getOrderByUser(@RequestParam Long userId) { 
		Iterable<Order> orders = orderService.getOrdersByUser(userId);
		return new ResponseEntity<Iterable<Order>>(orders, HttpStatus.OK);
	}

	@GetMapping("/{orderId}")
	public ResponseEntity<Order> getOrderById(@PathVariable Long orderId) {
		Order order = orderService.getOrderById(orderId);
		return new ResponseEntity<>(order, HttpStatus.OK);
	}
	
}
