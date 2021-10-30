package com.datadog.springJmsExample.services;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datadog.springJmsExample.domain.Order;
import com.datadog.springJmsExample.domain.User;
import com.datadog.springJmsExample.exceptions.SpringJmsExampleResourceNotFoundException;
import com.datadog.springJmsExample.repositories.OrderRepository;
import com.datadog.springJmsExample.repositories.UserRepository;

@Service
public class OrderService {
	
	private static Logger logger = LogManager.getLogger("springJmsExample-logger");

	@Autowired
	OrderRepository orderRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	OrderSenderService orderSenderService;
	
	public Order createOrUpdateOrder(Long userId, Order order) {
		User user = userRepository.findById(userId).get();
		System.out.println(user);
		order.setUser(user);
		Order newOrder = orderRepository.save(order);
		logger.info("Received new order: " + newOrder);
		orderSenderService.sendOrder(newOrder);
		return newOrder;
	}
	
	public Iterable<Order> getAllOrders() {
		return orderRepository.findAll();
	}
	
	public Order getOrderById(Long orderId) {
		Optional<Order> order = orderRepository.findById(orderId);
		if (!order.isPresent()) throw new SpringJmsExampleResourceNotFoundException("Cannot find order with ID " + orderId);
		return order.get();
	}
	
	public Iterable<Order> getOrdersByUser(Long userId) {
		User user = userRepository.findById(userId).get();
		return orderRepository.findByUser(user);
	}

}
