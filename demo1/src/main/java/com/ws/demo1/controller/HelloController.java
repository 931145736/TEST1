package com.ws.demo1.controller;


import com.alibaba.fastjson.JSONObject;
import com.ws.demo1.dto.HelloDto;
import com.ws.demo1.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;

import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 服务提供者
 */



@RestController
public class HelloController {

 /*   @Autowired
    private HelloDto helloDto;*/
    private final Logger logger = LoggerFactory.getLogger(HelloController.class);
  @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String index() throws InterruptedException {
        ServiceInstance instance = discoveryClient.getLocalServiceInstance();
        //测试超时
        /*int sleepTime = new Random().nextInt(3000);
        logger.info("延迟时间"+sleepTime);
        Thread.sleep(sleepTime);*/
        logger.info("/test,host:"+instance.getHost()+",service_id:"+instance.getServiceId());
        return "hello";
    }


    /**
     * 通过用户id查看用户信息
     *//*
    @RequestMapping("/findUserById")
    public User findUserById(Integer id){

        User user = new User(1,"张三",20);
        User user1 = new User(2,"李四",21);
        if(id==1){
            return user;
        }else{
            return  user1;
        }
    }

    *//**
     * 添加用户信息
     *//*
    @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    public String addUser(@RequestBody User user){

        logger.info("添加的对象"+ JSONObject.toJSONString(user));
        return  JSONObject.toJSONString(user);
    }*/

    /**
     * 带有request参数的请求
     */
    @RequestMapping(value = "/hello1",method = RequestMethod.GET)
    public String hello(@RequestParam String name){
        return "hello"+name;
    }
    /**
     * 带有header信息的请求
     */
    @RequestMapping(value = "/hello2",method = RequestMethod.GET)
    public User hello2(@RequestHeader String userName,@RequestHeader Integer userAge) throws UnsupportedEncodingException, InterruptedException {
        String name = new String(userName.getBytes("ISO-8859-1"),"UTF-8");
        logger.info("接收的参数"+name);

        return new User(8,name,userAge);
    }


    public static void main(String[] args){
        User user1 = new User(1,"a",10);
        User user2 = new User(2,"b",10);
        User user3 = new User(3,"c",10);
        User user4 = new User(3,"d",10);
        List<User> list = new ArrayList<>();
        list.add(user1);
        list.add(user2);
        list.add(user3);
        list.add(user4);
        Map<Integer,User> map = list.stream().collect(Collectors.toMap(e -> e.getUserId(),e -> e,(e1,e2) -> e2));
        System.out.println("list转map"+JSONObject.toJSONString(map));
    }
}
