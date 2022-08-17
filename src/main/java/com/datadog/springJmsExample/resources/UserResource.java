package com.datadog.springJmsExample.resources;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.datadog.springJmsExample.domain.User;
import com.datadog.springJmsExample.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserResource {
	
	@Autowired
	UserService userService;
	
	@GetMapping("")
	public ResponseEntity<Iterable<User>> getAllUsers() {
		Iterable<User> users = userService.getAllUsers();
		return new ResponseEntity<Iterable<User>>(users, HttpStatus.OK);
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<User> getAllUsers(@PathVariable Long userId) {
		User user = userService.getUserById(userId);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@PostMapping("/register")
	public ResponseEntity<User> registerUser(@Valid @RequestBody User user) {
		User newUser = userService.createOrUpdateUser(user);
		return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
	}

}
