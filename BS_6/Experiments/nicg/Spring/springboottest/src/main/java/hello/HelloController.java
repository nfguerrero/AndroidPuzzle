package hello;

import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {
	
    @RequestMapping("/")
    public String index() {
        return "Hello World!";
    }
}

class Person {
	
	String user;
	String address;
	
	public String getUser() {return user;}
	public String getAddress() {return address;}
	
}