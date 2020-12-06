package com.skilldistillery.doggiemeetup.services;

import java.util.List;

import com.skilldistillery.doggiemeetup.entities.User;

public interface UserService {

	User register(User user);
	
	List<User> index();
	
	User show(int id);
	
	User show(String username);
	
	User update(User user, int id);
	
	Boolean delete(int id);

}
