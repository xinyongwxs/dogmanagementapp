package com.ws.dog.management.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ws.dog.management.constants.CookieConstants;
import com.ws.dog.management.entity.User;
import com.ws.dog.management.model.UserCreateForm;
import com.ws.dog.management.repository.UserRepository;
import com.ws.dog.management.services.UserService;

import java.util.Collection;
import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;
    
    @Autowired
    private JwtService jwtService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> getUserById(long id) {
        LOGGER.debug("Getting user={}", id);
        return Optional.ofNullable(userRepository.findOne(id));
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        LOGGER.debug("Getting user by email={}", email.replaceFirst("@.*", "@***"));
        return userRepository.findOneByEmail(email);
    }

    @Override
    public Collection<User> getAllUsers() {
        LOGGER.debug("Getting all users");
        return userRepository.findAll(new Sort("email"));
    }

    @Override
    public User create(UserCreateForm form) {
        User user = new User();
        user.setEmail(form.getEmail());
        user.setPasswordHash(new BCryptPasswordEncoder().encode(form.getPassword()));
        user.setRole(form.getRole());
        return userRepository.save(user);
    }

	@Override
	public Boolean login(HttpServletResponse response, String userName, String password) {
		Boolean result = false;
		Optional<User> user = this.getUserByEmail(userName);
		User uu = user.get();
		if (userName.equals(uu.getEmail()) && (new BCryptPasswordEncoder().encode(password)).equals(uu.getPasswordHash())) {
			String jwtToken = jwtService.generateToken(userName, 20);
    		Cookie cookie = new Cookie(CookieConstants.ACCESS_TOKEN, jwtToken);
    		cookie.setHttpOnly(true);
    		cookie.setPath("/");
    		//cookie.setSecure(true);
    		response.addCookie(cookie);
			result = true;
		}
		return result;
	}

}
