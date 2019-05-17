package com.ws.demo1;

import com.ws.demo1.controller.HelloController;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest

@WebAppConfiguration
public class Demo1ApplicationTests {

	private MockMvc mockMvc;
	public void setUp()throws Exception{
		//mockMvc = MockMvcBuilder.standaloneSetup(new HelloController()).build();


	}

	@Test
	public void contextLoads() {
	}

}
