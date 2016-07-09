package com.example.controller;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ResponseBase {

    private static final Logger logger = LoggerFactory.getLogger(ResponseBase.class);


    private int code;
    private String message;
    private Object data;

    public static ResponseBase success(Object data) {
        ResponseBase res = new ResponseBase();
        res.setCode(0);
        res.setData(data);
        res.setMessage("success");
        return res;
    }

    public static ResponseBase fail(ServerException ex) {
        String message = "##Server Error:";
        String error = "Internal Error";
        if(ex.getMessage() != null) {
            message += ex.getMessage();
            error = ex.getMessage();
        }
        logger.error(message, ex);
        ResponseBase res = new ResponseBase();
        res.setCode(ex.getCode());
        res.setMessage(error);
        return res;
    }
}
