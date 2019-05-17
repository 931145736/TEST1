package com.ws.ribbonconsumer.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HelloService {

    @Autowired
    RestTemplate restTemplate; //注入restTemplate实例

    @HystrixCommand(fallbackMethod = "helloFallBack") //发生断路后回调方法，有问题
    public String helloService(){
        return restTemplate.getForEntity("http://hello/test",String.class).getBody();
    }
    public String helloFallBack(){
        return "error";
    }


}
