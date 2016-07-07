package com.example.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//@Configuration
public class MessageConvertConfiguration extends WebMvcConfigurerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(MessageConvertConfiguration.class);

    @Data
    @NoArgsConstructor
    private class WebApiResponse {
        private int code;
        private String error;
        private Object data;
    }

    private class JsonHttpMessageConverter extends AbstractHttpMessageConverter<Object> {
        @Override
        protected boolean supports(Class<?> clazz) {
            return true;
        }

        @Override
        protected Object readInternal(Class<?> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
            return null;
        }

        @Override
        protected void writeInternal(Object o, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
            outputMessage.getHeaders().add("Content-Type", "application/json");

            WebApiResponse response = new WebApiResponse();
            response.setCode(0);
            response.setData(o);
            outputMessage.getBody().write((new ObjectMapper()).writeValueAsBytes(response));
            outputMessage.getBody().flush();
        }
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        logger.info("##Install webapi message converter");
        JsonHttpMessageConverter converter = new JsonHttpMessageConverter();
        ArrayList<MediaType> typeList = new ArrayList<>();
        typeList.add(MediaType.ALL);
        converter.setSupportedMediaTypes(typeList);
        converters.add(0, converter);
    }

}
