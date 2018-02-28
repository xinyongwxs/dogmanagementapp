package com.ws.dog.management.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.ws.dog.management.entity.User;
import com.ws.dog.management.repository.UserRepository;
import org.springframework.security.core.authority.AuthorityUtils;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
	
	private final UserRepository userRepository;
	
	@Autowired
	public UserDetailsServiceImpl(UserRepository repository) {
		this.userRepository = repository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> userOpt = userRepository.findOneByEmail(username);
		User user = userOpt.get();
		return new org.springframework.security.core.userdetails.User(user.getEmail(),
				user.getPasswordHash(), AuthorityUtils.createAuthorityList(user.getRoleStrs()));
	}

}
