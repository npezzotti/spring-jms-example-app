package com.datadog.springJmsExample.receiptProcessor;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.datadog.springJmsExample.domain.Order;

import static com.datadog.springJmsExample.JMSConfig.ORDER_QUEUE;

@Component
public class ReceiptProcessor {
	
	private static Logger logger = LogManager.getLogger("springJmsExample-logger");

	@JmsListener(destination=ORDER_QUEUE)
	public void retrieveOrder(Order order) {
		Receipt receipt = this.processReceipt(order);
		logger.info("Sending receipt for order ID " + order.getid() + ": " + receipt);
	}
	
	public Receipt processReceipt(Order order) {
		logger.info("Processing receipt for order ID: " + order.getid());
		Date estimatedDeliveryTime = new Date();
		Receipt testReceipt = new Receipt(order.getUser().getFirstName(), 
				order.getUser().getEmail(), 
				order.getTotal(), 
				order.getBody(), 
				order.getPlacedAt(), 
				estimatedDeliveryTime);
		return testReceipt;
	}
	
}
