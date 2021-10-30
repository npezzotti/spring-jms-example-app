package com.datadog.springJmsExample.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.datadog.springJmsExample.domain.Order;

import static com.datadog.springJmsExample.JMSConfig.ORDER_QUEUE;

@Service
public class OrderSenderService {
	private static Logger logger = LogManager.getLogger("springJmsExample-logger");
	
	@Autowired
    private JmsTemplate jmsTemplate;
	
	public void sendOrder(Order order) {
		logger.info("Sending new order to order-queue: " + order);
		jmsTemplate.convertAndSend(ORDER_QUEUE, order);
	}
}
