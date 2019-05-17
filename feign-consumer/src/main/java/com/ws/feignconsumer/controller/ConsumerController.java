package com.ws.feignconsumer.controller;

import com.alibaba.fastjson.JSONObject;
import com.ws.feignconsumer.entity.User;
import com.ws.feignconsumer.service.HelloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class ConsumerController {


    @Resource
    private HelloService helloService;
    Logger logger = LoggerFactory.getLogger(ConsumerController.class);

    @RequestMapping(value = "/feign-consumer",method = RequestMethod.GET)
    public String helloConsumer(){
        return helloService.hello();
    }

    @RequestMapping(value = "/feign-consumer2",method = RequestMethod.GET)
    public String helloConsumer2(String name,Integer age){
        logger.info("参数名"+name);
        User user = helloService.hello2(name,age);
        logger.info("用户名"+user.getUserName());
        return JSONObject.toJSONString(user);
    }
}
