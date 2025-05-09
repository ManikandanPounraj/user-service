package com.rak.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.rak.user.model.User;
import com.rak.user.service.UserService;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Slf4j
public class UserController {

	private final UserService userservice;

	public UserController(UserService userservice) {
		this.userservice = userservice;
	}

	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user) {
		return ResponseEntity.ok(userservice.createUser(user));
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> getUserDetails(@PathVariable Long id) {
		User user = userservice.getUser(id);
		return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
	}

	@GetMapping
	public ResponseEntity<List<User>> getAllUsers() {
		return ResponseEntity.ok(userservice.getAllUsers());
	}

	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
		User updated = userservice.updateUser(id, user);
		return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
		 userservice.deleteUser(id);
		 return ResponseEntity.ok().build();
	}

	@GetMapping("/validate/{id}")
	public ResponseEntity<Void> validateUser(@PathVariable Long id) {
		return userservice.validateUser(id) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
	}
}
