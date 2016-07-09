package com.example.service;

import com.example.controller.ServerException;
import com.example.model.User;
import com.example.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        logger.info("Creating user: {}", user.getName());
        return userRepository.save(user);
    }

    @NotNull
    public User getUser(String name) throws ServerException {
        User user = userRepository.findByName(name);
        if(null == user) {
            if(name != null) {
                user = createUser(new User(name, User.Gender.male));
            } else {
                throw new ServerException("User not found", 1000);
            }
        }
        return user;
    }
}
