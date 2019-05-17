package com.ws.apigateway.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/local/hello")
    public String test(){
        return "hello shuaishuai";
    }
}
