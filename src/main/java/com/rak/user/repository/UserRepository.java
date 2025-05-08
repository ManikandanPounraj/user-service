package com.rak.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rak.user.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
