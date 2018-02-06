package com.ws.dog.management.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ws.dog.management.entity.User;

public class UserPrincipal implements UserDetails {
	private static final long serialVersionUID = -2580056421482365764L;
	private User user;
	
	public UserPrincipal(User user) {
		this.user = user;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<CustomAuthority> authorities = new ArrayList<CustomAuthority>();
		CustomAuthority authority = new CustomAuthority(this.user.getRole());
		authorities.add(authority);
		return authorities;
	}

	@Override
	public String getPassword() {
		return this.user.getPasswordHash();
	}

	@Override
	public String getUsername() {
		return this.user.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

}
