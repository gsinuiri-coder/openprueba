package com.thenews.userprofile.domain.service;

import com.thenews.userprofile.domain.model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(Long userId);

    User createUser(User user);
    User updateUser(Long userId, User userRequest);
    ResponseEntity<?> deleteUser(Long userId);
}
