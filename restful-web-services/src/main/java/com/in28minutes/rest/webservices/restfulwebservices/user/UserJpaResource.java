package com.in28minutes.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserJpaResource {

	@Autowired
	private UserRepository userRespository;
	
	@Autowired
	private PostRepository postRepository;

	// find all users
	@GetMapping(path = "/jpa/users")
	public List<Users> retrieveAllUser() {
		return userRespository.findAll();
	}

	// find specific user
	@GetMapping(path = "/jpa/users/{id}")
	public EntityModel<Users> findUser(@PathVariable int id) {
		Optional<Users> user = userRespository.findById(id);
		
		if(user.isEmpty()) {
			throw new UserNotFoundException("Id-" + id);
		}
		EntityModel <Users> model = EntityModel.of(user.get());
		WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(
				WebMvcLinkBuilder.methodOn(this.getClass()).retrieveAllUser());
		
		model.add(linkTo.withRel("all-users"));
		return model;
	}

	@PostMapping(path = "/jpa/users")
	public ResponseEntity<Object> save(@Valid @RequestBody Users user) {
		Users savedUser = userRespository.save(user);

		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId())
				.toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping(path = "/jpa/delete/{id}")
	public void deleteUser(@PathVariable int id) {
		userRespository.deleteById(id);
	}
	
	@GetMapping(path = "/jpa/users/{id}/posts")
	public List<Post> findPost(@PathVariable int id) {
		Optional<Users> user = userRespository.findById(id);
		
		if(user.isEmpty()) {
			throw new UserNotFoundException("Id-" + id);
		}
		
		return user.get().getPosts();
	}
	
	@PostMapping(path = "/jpa/users/{id}/post")
	public ResponseEntity<Object> save(@PathVariable int id, @RequestBody Post post) {
		Optional<Users> user = userRespository.findById(id);
		if(user.isEmpty()) {
			throw new UserNotFoundException("Id-" + id);
		}
		
		post.setUser(user.get());
		postRepository.save(post);

		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(post.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}
}
