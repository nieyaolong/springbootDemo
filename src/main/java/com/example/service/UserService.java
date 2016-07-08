package com.example.service;

import com.example.model.User;
import com.example.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        logger.info("Creating user: {}", user.getName());
        return userRepository.save(user);
    }

    public User getUser(String name) {
        User user = userRepository.findByName(name);
        if(null == user) {
            user = createUser(new User(name, User.Gender.female));
        }
        return user;
    }
}
