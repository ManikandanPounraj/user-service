package com.rak.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.rak.user.model.User;
import com.rak.user.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

	private final UserService service;

	public UserController(UserService service) {
		this.service = service;
	}

	@PostMapping
	public ResponseEntity<User> create(@RequestBody User user) {
		return ResponseEntity.ok(service.createUser(user));
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> get(@PathVariable Long id) {
		User user = service.getUser(id);
		return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
	}

	@GetMapping
	public ResponseEntity<List<User>> getAll() {
		return ResponseEntity.ok(service.getAllUsers());
	}

	@PutMapping("/{id}")
	public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User user) {
		User updated = service.updateUser(id, user);
		return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		return service.deleteUser(id) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
	}

	@GetMapping("/validate/{id}")
	public ResponseEntity<Void> validate(@PathVariable Long id) {
		return service.validateUser(id) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
	}
}
