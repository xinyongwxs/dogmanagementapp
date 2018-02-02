package com.ws.dog.management.context;

import org.springframework.web.context.annotation.SessionScope;

@SessionScope
public class UserContext {
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
