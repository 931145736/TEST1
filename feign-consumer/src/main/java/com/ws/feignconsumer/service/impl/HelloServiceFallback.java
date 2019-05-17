package com.ws.feignconsumer.service.impl;

import com.ws.feignconsumer.entity.User;
import com.ws.feignconsumer.service.HelloService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestHeader;

@Component
public class HelloServiceFallback implements HelloService {
    @Override
    public String hello() {
        return "error";
    }

    @Override
    public String hello1(String name) {
        return null;
    }

    @Override
    public User hello2(@RequestHeader("userName")String userName, @RequestHeader("userAge")Integer userAge) {
        return new User(0,"未知",0);
    }
}
