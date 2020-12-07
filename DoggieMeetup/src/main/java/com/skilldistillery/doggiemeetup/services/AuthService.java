package com.skilldistillery.doggiemeetup.services;

import com.skilldistillery.doggiemeetup.entities.User;

public interface AuthService {
	
	public User register(User user) throws Exception;

	public User getUser(String username);

}
