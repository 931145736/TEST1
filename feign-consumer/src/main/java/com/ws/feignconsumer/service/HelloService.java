package com.ws.feignconsumer.service;

import com.ws.feignconsumer.config.DisableHystrixConfiguration;
import com.ws.feignconsumer.entity.User;
import com.ws.feignconsumer.service.impl.HelloServiceFallback;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * feign客户端
 * @FeignClient name:服务提供者,configuration: 引用配置类,fallback:断路回调
 */

@FeignClient(name = "hello",fallback = HelloServiceFallback.class)  //代表创建feign客户端的同时也创建了一个hello的ribbon客户端,引入配置类
public interface HelloService {

    /**
     * @RequestParam  value 不能为空
     * @RequestHeader value 不能为空
     * @return
     */

    @RequestMapping("/test")
    String hello();


    @RequestMapping(value = "/hello1",method = RequestMethod.GET)
    String hello1(@RequestParam("name") String name);

    @RequestMapping(value = "/hello2",method = RequestMethod.GET)
    User hello2(@RequestHeader("userName") String userName, @RequestHeader("userAge") Integer userAge);

}
