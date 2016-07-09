package com.example.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler{
    @ExceptionHandler
    @ResponseBody
    ResponseEntity<?> handleControllerException(HttpServletRequest request, Throwable tr) {
        ServerException ex = new ServerException(tr, -1);
        ResponseBase res = ResponseBase.fail(ex);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
