package com.ws.apigateway;

import com.netflix.zuul.FilterProcessor;
import com.ws.apigateway.filter.DidiErrorAttributes;
import com.ws.apigateway.filter.DidiFilterProcesser;
import com.ws.apigateway.filter.PreFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.discovery.PatternServiceRouteMapper;
import org.springframework.context.annotation.Bean;

/**
 * API网关重要性：
 * 1. 它作为系统的统一入口，屏蔽了系统内部各个微服务的细节
 * 2.它可以与服务治理框架结合，实习自动化的服务实例维护以及负载均衡的路由转发
 * 3.它可以实现接口权限校验与微服务业务逻辑的解耦
 * 4.通过服务网关中的过滤器，在各生命周期中去校验请求的内容，将原本在对外服务层做的校验前移，保证了微服务的无状态性
 *   同时降低了微服务的测试难度，让服务本身更集中关注与业务逻辑的处理
 *
 */



@EnableZuulProxy //开启API网关服务
@SpringBootApplication
public class ApiGatewayApplication {


	public static void main(String[] args) {
		//启用自定义的核心处理器
		FilterProcessor.setProcessor(new DidiFilterProcesser());
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

	/**
	 * 实例化过滤器
	 * @return
	 */
	@Bean
	public PreFilter accessFilter(){
		return new PreFilter();
	}

	/**
	 * 实例化自定义异常返回信息类
	 * @return
	 */
	@Bean
	public DefaultErrorAttributes defaultErrorAttributes(){
		return new DidiErrorAttributes();
	}


	@Bean //自定义路由映射规则，按照版本号匹配表达式进行定义
	public PatternServiceRouteMapper serviceRouteMapper(){
		return new PatternServiceRouteMapper("(?<name>^.+)-(?<version>v.+$)","${version}/${name}");
	}
}
