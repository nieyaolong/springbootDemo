package com.example.config;

import com.fasterxml.classmate.TypeResolver;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.schema.WildcardType;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Date;

import static springfox.documentation.schema.AlternateTypeRules.newRule;

@Configuration
@EnableSwagger2
@AutoConfigureAfter(WebMvcAutoConfiguration.class)
public class SwaggerConfig {

    private Logger logger = org.slf4j.LoggerFactory.getLogger(SwaggerConfig.class);


    @Autowired
    private TypeResolver typeResolver;

    @Bean
    public Docket api() {
        System.out.println("###test log");
        ArrayList<ResponseMessage> messageList = new ArrayList<>();
        messageList.add(new ResponseMessageBuilder().code(500).message("internal error").responseModel(new ModelRef("Error")).build());

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("example")
                .apiInfo(getApiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build().pathMapping("/")
                .directModelSubstitute(Date.class, String.class)
                .genericModelSubstitutes(ResponseEntity.class)
                .alternateTypeRules(newRule(typeResolver.resolve(DeferredResult.class, typeResolver.resolve(ResponseEntity.class, WildcardType.class)),
                        typeResolver.resolve(ResponseEntity.class, WildcardType.class)))
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET,messageList)
                .forCodeGeneration(true);
    }

    private ApiInfo getApiInfo() {
        return new ApiInfo("title", "desc", "1.0", "", new Contact("name", "url", "email"), "", "");
    }
}
