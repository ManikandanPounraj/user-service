package com.rak.user.service;

import org.springframework.stereotype.Service;

import com.rak.user.exception.UserNotFoundException;
import com.rak.user.model.User;
import com.rak.user.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

import java.util.List;


@Service
@Slf4j
public class UserService {
	private final UserRepository userRepo;

	public UserService(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

	public User createUser(User user) {
		log.info("Create User with UserObj = {}",user);
		User userObj = userRepo.findByEmail(user.getEmail());
		if(userObj != null) {
			log.info("User Already registered with the same EmailId....");
		return userObj;
		}else {
			return userRepo.save(user);
		}
	}

	public User getUser(Long userId) {
		log.info("Create User with UserObj = {}",userId);
		return userRepo.findById(userId).orElseThrow(() -> {
			log.error("User not found with ID: {}", userId);
			return new UserNotFoundException("User not found with id: " + userId);
		});
	}

	public List<User> getAllUsers() {
		log.info("Get All Users");
		return userRepo.findAll();
	}

	public User updateUser(Long userId, User updatedUser) {
		log.info("Update User for id = {}, userObj = {}", userId, updatedUser);
		return userRepo.findById(userId).map(user -> {
			user.setName(updatedUser.getName());
			//user.setEmail(updatedUser.getEmail());
			return userRepo.save(user);
		}).orElseThrow(() -> {
			log.error("User not found with ID: {}", userId);
			return new UserNotFoundException("User not found with id: " + userId);
		});
	}

	public void deleteUser(Long id) {
		log.info("Delete User for userId = {}",id);
		if (getUser(id) != null) {
			userRepo.deleteById(id);
		}
		
	}

	public boolean validateUser(Long id) {
		log.info("Validate User for userId = {}", id);
		if (getUser(id) != null) {
			return true;
		}
			return false;
		
	}
}

