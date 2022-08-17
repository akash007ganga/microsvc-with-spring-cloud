package com.in28minutes.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResource {

	@Autowired
	private UserDaoService service;

	// find all users
	@GetMapping(path = "/users")
	public List<Users> retrieveAllUser() {
		return service.findAll();
	}

	// find specific user
	@GetMapping(path = "/users/{id}")
	public EntityModel<Users> findUser(@PathVariable int id) {
		Users user = service.findOne(id);
		if(Objects.isNull(user)) {
			throw new UserNotFoundException("Id-" + id);
		}
		EntityModel <Users> model = EntityModel.of(user);
		WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(
				WebMvcLinkBuilder.methodOn(this.getClass()).retrieveAllUser());
		
		model.add(linkTo.withRel("all-users"));
		return model;
	}

	@PostMapping(path = "/users")
	public ResponseEntity<Object> save(@Valid @RequestBody Users user) {
		Users savedUser = service.save(user);

		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId())
				.toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping(path = "/delete/{id}")
	public void deleteUser(@PathVariable int id) {
		Users user = service.delete(id);
		if(Objects.isNull(user)) {
			throw new UserNotFoundException("Id-" + id);
		}
	}
}
