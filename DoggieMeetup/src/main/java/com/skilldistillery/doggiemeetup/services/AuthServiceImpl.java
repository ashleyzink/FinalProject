package com.skilldistillery.doggiemeetup.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.skilldistillery.doggiemeetup.entities.User;
import com.skilldistillery.doggiemeetup.repositories.UserRepository;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private UserRepository userRepo;

	@Override
	public User register(User user) throws Exception {

		
		if (userRepo.findByEmail(user.getEmail()) != null) {
			throw new Exception("Email already in use");
		} else if (userRepo.findByUsername(user.getUsername()) != null) {
			throw new Exception("Username already in use");
		} else {

			// encrypt and set the password for the User
			user.setPassword(encoder.encode(user.getPassword()));

			// set the enabled field of the object to true
			user.setEnabled(true);

			// set the role field of the object to "standard"
			user.setRole("standard");

			// saveAndFlush the user using the UserRepo
			userRepo.saveAndFlush(user);

			// return the User object
			System.out.println(user);
		}
		return user;
	}

	@Override
	public User getUser(String username) {
		return userRepo.findByUsername(username);
	}

}
