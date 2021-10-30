package com.datadog.springJmsExample.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datadog.springJmsExample.domain.OrderItem;
import com.datadog.springJmsExample.repositories.OrderItemRepository;

@Service
public class OrderItemService {

	@Autowired
	OrderItemRepository orderItemRepository;
	
	public void addOrderItem(OrderItem orderItem) {
		orderItemRepository.save(orderItem);
	}
}
