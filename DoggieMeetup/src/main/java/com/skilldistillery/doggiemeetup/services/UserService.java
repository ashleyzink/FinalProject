package com.skilldistillery.doggiemeetup.services;

import java.util.List;

import com.skilldistillery.doggiemeetup.entities.User;

public interface UserService {

	User register(User user);
	
	List<User> index();
	
	User show(int id);
	
	User show(String username);
	
	User update(String username, User user, int id);
	
	Boolean delete(String username, int id);
	
	Boolean disable(String username, int id);
	
	Boolean enable(String username, int id);
	
	User updatePassword(User user, int id);

}
