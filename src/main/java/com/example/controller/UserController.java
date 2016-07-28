package com.example.controller;

import com.example.domain.model.UserInfo;
import com.example.model.User;
import com.example.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @ApiOperation(value = "get user", notes = "get user info by name")
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    @Secured("ROLE_ADMIN")
    public UserInfo getUser(@RequestParam(required = false) String name) throws Exception {

        logger.error("get user info {}", name);
        User user = userService.getUser(name);
        return UserInfo.getUser(user);
    }

    @ApiOperation(value = "get user list", notes = "get user info by name")
    @RequestMapping(value = "/userlist", method = RequestMethod.GET)
    @Secured("ROLE_USER")
    public List<UserInfo> getUserLIST(@RequestParam(required = false) String name) throws Exception {

        logger.error("get user list {}", name);
        ArrayList<UserInfo> list = new ArrayList<>();
        User user = userService.getUser(name);
        list.add(UserInfo.getUser(user));
        return list;
    }

}