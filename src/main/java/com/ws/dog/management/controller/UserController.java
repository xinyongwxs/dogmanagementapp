package com.ws.dog.management.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ws.dog.management.services.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public Boolean login(HttpServletResponse response,
			@RequestParam(value = "username", required = true) String userName, 
    		@RequestParam(value = "password", required = true) String password) {
		return userService.login(response, userName, password);
	}
}
