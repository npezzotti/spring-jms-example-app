package com.datadog.springJmsExample.services;

import static com.datadog.springJmsExample.JMSConfig.ORDER_QUEUE;

import java.util.Optional;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
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
    private JmsTemplate jmsTemplate;
	
	public Order createOrUpdateOrder(@Valid Long userId, Order order) {
		Optional<User> user = userRepository.findById(userId);
		if (!user.isPresent()) throw new SpringJmsExampleResourceNotFoundException("User with ID " + userId + "does not exist");
		order.setUser(user.get());
		Order newOrder = orderRepository.save(order);
		logger.info("Received new order: " + newOrder);
		sendOrder(newOrder);
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
		Optional<User> user = userRepository.findById(userId);
		if (!user.isPresent()) throw new SpringJmsExampleResourceNotFoundException("User with ID " + userId + "does not exist");
		return orderRepository.findByUser(user.get());
	}
	
	public void sendOrder(Order order) {
		logger.info("Sending new order to order-queue: " + order);
		jmsTemplate.convertAndSend(ORDER_QUEUE, order);
	}

}
