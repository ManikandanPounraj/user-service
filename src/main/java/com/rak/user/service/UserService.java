package com.rak.user.service;

import org.springframework.stereotype.Service;

import com.rak.user.model.User;
import com.rak.user.repository.UserRepository;

import java.util.List;

@Service
public class UserService {
	private final UserRepository repo;

	public UserService(UserRepository repo) {
		this.repo = repo;
	}

	public User createUser(User user) {
		return repo.save(user);
	}

	public User getUser(Long id) {
		return repo.findById(id).orElse(null);
	}

	public List<User> getAllUsers() {
		return repo.findAll();
	}

	public User updateUser(Long id, User updatedUser) {
		return repo.findById(id).map(user -> {
			user.setName(updatedUser.getName());
			user.setEmail(updatedUser.getEmail());
			return repo.save(user);
		}).orElse(null);
	}

	public boolean deleteUser(Long id) {
		if (repo.existsById(id)) {
			repo.deleteById(id);
			return true;
		}
		return false;
	}

	public boolean validateUser(Long id) {
		return repo.existsById(id);
	}
}

