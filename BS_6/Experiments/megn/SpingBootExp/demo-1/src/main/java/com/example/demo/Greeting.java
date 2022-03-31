package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Greeting {
	
	@RequestMapping("/hello")
	
	public @ResponseBody String hello() {
		return "Hello Com S 309!";
	}
}
