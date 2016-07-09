package com.example.controller;

import com.example.domain.model.UserInfo;
import com.example.model.User;
import com.example.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @ApiOperation(value = "get user", notes = "get user info by name")
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public UserInfo getUser(@RequestParam(required = false) String name) throws Exception {

        logger.error("get user info {}", name);
        User user = userService.getUser(name);
        return UserInfo.getUser(user);
    }
}