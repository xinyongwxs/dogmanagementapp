package com.ws.dog.management.preload;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ws.dog.management.entity.Role;
import com.ws.dog.management.entity.RoleEntity;
import com.ws.dog.management.entity.User;
import com.ws.dog.management.repository.UserRepository;

@Component
public class DatabaseLoader implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		User adminUser = new User();
		adminUser.setEmail("xinyongwxs@163.com");
		adminUser.setPasswordHash("Initial1");
		Set<RoleEntity> roleSet = new HashSet<RoleEntity>();
		roleSet.add(new RoleEntity(Role.ADMIN));
		List<User> userList = new ArrayList<User>();
		userList.add(adminUser);
		userRepository.save(userList);
	}

}
