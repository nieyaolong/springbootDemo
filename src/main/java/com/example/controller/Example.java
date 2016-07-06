package com.example.controller;

import com.example.model.User;
import io.swagger.annotations.ApiOperation;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class Example {

    @ApiOperation(value = "test api", notes = "return paramter name")
    @RequestMapping("/user")
    User home(String name) {
        return new User(name, User.Gender.male);
    }
}