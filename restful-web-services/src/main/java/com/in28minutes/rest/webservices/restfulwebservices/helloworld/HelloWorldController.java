package com.in28minutes.rest.webservices.restfulwebservices.helloworld;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	
	@Autowired
	private MessageSource messageSource;

	//GET http://localhost:8080/hello-world
	@GetMapping(path = "/hello-world")
	public String helloWorld() {
		return "hello-world";
	}
	
	//GET http://localhost:8080/hello-world-bean
	@GetMapping(path = "/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("hello-world");
	}
	
	//GET http://localhost:8080/hello-world/path-variable/ranga
	@GetMapping(path = "/hello-world/path-variable/{name}")
	public HelloWorldBean helloWorldBean(@PathVariable String name) {
		return new HelloWorldBean(String.format("hello %s", name));
	}
	
	@GetMapping(path = "/hello-world-internationalized")
	public String helloWorldInternationalized(@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
		return messageSource.getMessage("good.morning.message", null, "default message", locale);
	}
	
	@GetMapping(path = "/hello-world-internationalized2")
	public String helloWorldInternationalized2() {
		return messageSource.getMessage("good.morning.message", null, "default message", LocaleContextHolder.getLocale());
	}
	
}
