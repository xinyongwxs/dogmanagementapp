package com.ws.dog.management.services.impl;

import java.util.Collection;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import com.ws.dog.management.entity.User;
import com.ws.dog.management.model.UserCreateForm;
import com.ws.dog.management.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Override
	public Optional<User> getUserById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<User> getUserByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<User> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User create(UserCreateForm form) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean login(HttpServletResponse response, String userName, String password) {
		// TODO Auto-generated method stub
		return null;
	}

}
