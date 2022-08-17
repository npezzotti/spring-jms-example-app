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
		logger.info("Emailing receipt for order ID " + order.getid() + ": " + receipt);
//		...code that generates an email to customer
	}
	
	public Receipt processReceipt(Order order) {
		logger.info("Processing receipt for order ID: " + order.getid());
		Date date = new Date();
		long timeMilli = date.getTime();
		Date estimatedDeliveryTime = new Date(timeMilli + 600000);
		Receipt testReceipt = new Receipt(
				order.getUser(),
				order.getTotal(),
				order.getItem(), 
				order.getPlacedAt(), 
				estimatedDeliveryTime);
		return testReceipt;
	}

}
