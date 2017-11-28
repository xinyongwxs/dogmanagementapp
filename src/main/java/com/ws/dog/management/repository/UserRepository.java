package com.ws.dog.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ws.dog.management.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findOneByUsername(String username);
}
