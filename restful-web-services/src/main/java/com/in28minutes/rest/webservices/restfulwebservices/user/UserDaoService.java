package com.in28minutes.rest.webservices.restfulwebservices.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {

	private static ArrayList<Users> users = new ArrayList<>();

	private int userCount = 3;

	static {
		users.add(new Users(1, "Adam", new Date()));
		users.add(new Users(2, "Eve", new Date()));
		users.add(new Users(3, "Jack", new Date()));
	}

	public List<Users> findAll() {
		return users;
	}

	public Users findOne(int id) {
		return users.stream().filter(rec -> rec.getId() == id).findFirst().orElse(null);
	}

	public Users save(Users user) {
		if (user.getId() == null) {
			user.setId(++userCount);
		}

		users.add(user);
		return user;
	}
	
	public Users delete(int id) {
		Iterator<Users> it = users.iterator();
		while(it.hasNext()) {
			Users user = it.next();
			if(user.getId() == id) {
				it.remove();
				return user;
			}
		}
		
		return null;
	}

}
