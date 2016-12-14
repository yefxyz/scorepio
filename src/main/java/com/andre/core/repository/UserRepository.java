package com.andre.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.andre.core.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);
	
}
