package com.datadog.springJmsExample;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.datadog.springJmsExample.domain.OrderItem;
import com.datadog.springJmsExample.domain.Order;
import com.datadog.springJmsExample.domain.Product;
import com.datadog.springJmsExample.domain.User;
import com.datadog.springJmsExample.repositories.OrderItemRepository;
import com.datadog.springJmsExample.repositories.OrderRepository;
import com.datadog.springJmsExample.repositories.ProductRepository;
import com.datadog.springJmsExample.repositories.UserRepository;

@SpringBootApplication
public class SpringJmsExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringJmsExampleApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(
			UserRepository userRepository, 
			OrderRepository orderRepository, 
			ProductRepository productRepository,
			OrderItemRepository itemRepository) {
		return (args) -> {
//			Test user
			User testUser = new User();
			testUser.setFirstName("Nathan");
			testUser.setLastName("Pezzotti");
			testUser.setEmail("npezzotti@email.com");
			testUser.setPassword("password");
			User newUser = userRepository.save(testUser);
			
//			Inventory
			Product coffee = new Product();
			coffee.setName("coffee");
			coffee.setDescription("coffee");
			coffee.setPrice(2.00);
			productRepository.save(coffee);
			
			Product tea = new Product();
			tea.setName("tea");
			tea.setDescription("tea");
			tea.setPrice(1.50);
			productRepository.save(tea);
			
			Product soda = new Product();
			soda.setName("soda");
			soda.setDescription("soda");
			soda.setPrice(2.50);
			productRepository.save(soda);
			
//			Test Order
			Order testOrder = new Order();
			testOrder.setBody("Hello");
			testOrder.setTotal(100.00);
			testOrder.setUser(newUser);
			Order newOrder = orderRepository.save(testOrder);
			
//			Test Order Item
			OrderItem testItem = new OrderItem();
			testItem.setProduct(coffee);
			testItem.setQuantity(1);
			testItem.setOrder(newOrder);
			itemRepository.save(testItem);
		};
	}
}
