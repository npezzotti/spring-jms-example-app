package com.datadog.springJmsExample;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.datadog.springJmsExample.domain.Order;
import com.datadog.springJmsExample.domain.User;
import com.datadog.springJmsExample.repositories.OrderRepository;
import com.datadog.springJmsExample.repositories.UserRepository;

@SpringBootApplication
public class SpringJmsExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringJmsExampleApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(
			UserRepository userRepository, 
			OrderRepository orderRepository) {
		return (args) -> {
//			Test user
			User testUser = new User();
			testUser.setFirstName("Nathan");
			testUser.setLastName("Pezzotti");
			testUser.setEmail("npezzotti@email.com");
			testUser.setPassword("password");
			User newUser = userRepository.save(testUser);		
			
//			Test Order
			Order testOrder = new Order();
			testOrder.setBody("Hello");
			testOrder.setTotal(100.00);
			testOrder.setUser(newUser);
			orderRepository.save(testOrder);
		};
	}
}
