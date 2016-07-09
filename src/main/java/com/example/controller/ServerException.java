package com.example.controller;

import lombok.Data;

@Data
public class ServerException extends Exception{
    private int code;

    public ServerException(Throwable tr, int code) {
        super(tr.getMessage());
        this.setStackTrace(tr.getStackTrace());
        this.code = code;
    }

    public ServerException(String message, int code) {
        super(message);
        this.code = code;
    }
}
