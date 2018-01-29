package com.ws.dog.management.services;

import java.util.Collection;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import com.ws.dog.management.entity.User;
import com.ws.dog.management.model.UserCreateForm;

public interface UserService {
    Optional<User> getUserById(long id);

    Optional<User> getUserByEmail(String email);

    Collection<User> getAllUsers();

    User create(UserCreateForm form);
    
    Boolean login(HttpServletResponse response, String userName, String password);

}
