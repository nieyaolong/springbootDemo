package com.example.controller;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableAutoConfiguration
public class Example {

    private class testObj {
        private String result;

        testObj(String result) {
            this.result = result;
        }
    }

    @RequestMapping("/test")
    testObj home() {
        return new testObj("Hello World!");
    }
}