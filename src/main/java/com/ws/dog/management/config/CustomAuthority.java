package com.ws.dog.management.config;

import org.springframework.security.core.GrantedAuthority;

import com.ws.dog.management.entity.Role;

public class CustomAuthority implements GrantedAuthority {
	private static final long serialVersionUID = -5266428489633226208L;
	private Role role;
	
	public CustomAuthority(Role role) {
		this.role = role;
	}
	
	@Override
	public String getAuthority() {
		return this.role.name();
	}

}
