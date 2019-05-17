package com.ws.ribbonconsumer.controller;

import com.alibaba.fastjson.JSONObject;
import com.ws.ribbonconsumer.entity.User;
import com.ws.ribbonconsumer.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ConsumerController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HelloService helloService;

    @RequestMapping(value = "/ribbon-consumer",method = RequestMethod.GET)
    public String test1(){

        //return restTemplate.getForEntity("http://hello/hello",String.class).getBody();
        return helloService.helloService();
    }

    @RequestMapping(value = "/ribbon-findUser",method = RequestMethod.GET)
    public User findUser(Integer id){
     // ResponseEntity<User> responseEntity = restTemplate.getForEntity("http://hello/findUserById?id={1}",User.class,id);
      //User user = responseEntity.getBody();
        User user = restTemplate.getForObject("http://hello/findUserById?id={1}",User.class,id);
        return user;
    }

    @RequestMapping(value = "/ribbon-addUser",method = RequestMethod.POST)
    public String addUser(){
        User user = new User(5,"刘备",108);

        //ResponseEntity<String> responseEntity= restTemplate.postForEntity("http://hello/addUser",user,String.class);
        URI uri =restTemplate.postForLocation("http://hello/addUser",user);
        return JSONObject.toJSONString(uri);
    }

}
