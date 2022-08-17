package com.datadog.springJmsExample.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datadog.springJmsExample.domain.User;
import com.datadog.springJmsExample.exceptions.SpringJmsExampleResourceNotFoundException;
import com.datadog.springJmsExample.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	public User createOrUpdateUser(User user) {
		return userRepository.save(user);
	}

	public Iterable<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	public User getUserById(Long userId) {
		Optional<User> user = userRepository.findById(userId);
		System.out.println(user.isPresent());
		if (!user.isPresent()) throw new SpringJmsExampleResourceNotFoundException("User with ID " + userId + "does not exist");
		return user.get();
	}

}
