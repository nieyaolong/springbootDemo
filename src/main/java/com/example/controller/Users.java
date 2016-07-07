package com.example.controller;

import com.example.model.User;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Users {

    private static final Logger logger = LoggerFactory.getLogger(Users.class);


    @ApiOperation(value = "get user", notes = "get user info by name")
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    User getUser(String name) {

        logger.error("get user info {}", name);
        return new User(name, User.Gender.male);
    }
}