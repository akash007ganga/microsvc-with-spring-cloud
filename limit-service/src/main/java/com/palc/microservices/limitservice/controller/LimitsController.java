package com.palc.microservices.limitservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.palc.microservices.limitservice.bean.Limit;
import com.palc.microservices.limitservice.configuration.Configuration;

@RestController
public class LimitsController {
	
	@Autowired
	private Configuration configuration;

	@GetMapping ("/limits")
	public Limit retrieveLimits() {
		//return new Limit(1, 1000);
		return new Limit(configuration.getMinimum(), configuration.getMaximum());
	}
}
