package com.in28minutes.rest.webservices.restfulwebservices.versioning;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningController {

	
	@GetMapping (value = "/person/v1")
	public PersonV1 getPersonV1() {
		return new PersonV1("Bob");
	}
	
	@GetMapping (value = "/person/v2")
	public PersonV2 getPersonV2() {
		return new PersonV2(new Name("Bob", "Chirlie"));
	}
	
	@GetMapping (value = "/person", params = "version=1")
	public PersonV1 getPersonV1ByReqParam() {
		return new PersonV1("Bob");
	}
	
	@GetMapping (value = "/person", params = "version=2")
	public PersonV2 getPersonV2ByReqParam() {
		return new PersonV2(new Name("Bob", "Chirlie"));
	}
	
	@GetMapping (value = "/person", headers = "X-API-VERSION=1")
	public PersonV1 getPersonV1ByHeader() {
		return new PersonV1("Bob");
	}
	
	@GetMapping (value = "/person", headers = "X-API-VERSION=2")
	public PersonV2 getPersonV2ByHeader() {
		return new PersonV2(new Name("Bob", "Chirlie"));
	}
	
	@GetMapping (value = "/person", produces = "application/v1+json")
	public PersonV1 getPersonV1ByAccept() {
		return new PersonV1("Bob");
	}
	
	@GetMapping (value = "/person", produces = "application/v2+json")
	public PersonV2 getPersonV2ByAccept() {
		return new PersonV2(new Name("Bob", "Chirlie"));
	}
	
}
