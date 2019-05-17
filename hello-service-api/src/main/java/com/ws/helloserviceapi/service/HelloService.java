package com.ws.helloserviceapi.service;

import com.ws.helloserviceapi.dto.User;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@RequestMapping("/refactor")
public interface HelloService {


    @RequestMapping(value = "/hello3",method = RequestMethod.GET)
    String hello1(@RequestParam("name") String name);

    @RequestMapping(value = "/hello4",method = RequestMethod.GET)
    User hello2( @RequestHeader("userName") String userName, @RequestHeader("userAge") Integer userAge);

}
