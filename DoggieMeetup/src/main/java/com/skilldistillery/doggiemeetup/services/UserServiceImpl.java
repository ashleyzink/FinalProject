package com.skilldistillery.doggiemeetup.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.skilldistillery.doggiemeetup.entities.User;
import com.skilldistillery.doggiemeetup.repositories.UserRepository;

@Repository
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepo;

	@Autowired
	private PasswordEncoder encoder;
	
	@Override
	public User register(User user) {
		String encodedPW = encoder.encode(user.getPassword());
		user.setPassword(encodedPW); // only persist encoded password
		// set other fields to default values
		user.setEnabled(true);
		userRepo.saveAndFlush(user);
		user.setRole("standard");
		return user;
	}

	@Override
	public List<User> index() {
		return userRepo.findAll();
	}

	@Override
	public User show(int id) {
		Optional<User> userOpt = userRepo.findById(id);
		if (!userOpt.isPresent()) {
			return null;
		}
		return userOpt.get();
	}

	@Override
	public User show(String username) {
		return userRepo.findByUsername(username);
	}

	@Override
	public User update(String username, User user, int id) {
		if (user.getId() != id) {
			return null;
		}
		Optional<User> userOpt = userRepo.findById(id);
		if (!userOpt.isPresent()) {
			return null;
		}
		User dbUser = userOpt.get();
		if (dbUser.getUsername().equals(username)) {
			
		if (user.getDateOption() != null) { dbUser.setDateOption(user.getDateOption()); }
		if (user.getFirstName() != null) { dbUser.setFirstName(user.getFirstName()); }
		if (user.getLastName() != null) { dbUser.setLastName(user.getLastName()); }
		if (user.getUsername() != null) { dbUser.setUsername(user.getUsername()); }
		if (user.getEmail() != null) { dbUser.setEmail(user.getEmail()); }
		if (user.getRole() != null) { dbUser.setRole(user.getRole()); }
		if (user.getEnabled() != null) { dbUser.setEnabled(user.getEnabled()); }
		if (user.getProfilePhotoUrl() != null) { dbUser.setProfilePhotoUrl(user.getProfilePhotoUrl()); }
		if (user.getBio() != null) { dbUser.setBio(user.getBio()); }
		if (user.getProfilePrivate() != null) { dbUser.setProfilePrivate(user.getProfilePrivate()); }
		if (user.getLocationPrivate() != null) { dbUser.setLocationPrivate(user.getLocationPrivate()); }
		return userRepo.saveAndFlush(dbUser);
		}
		else { return null;}
	}
	

	@Override
	public User updatePassword(User user, int id) {
		if (user.getId() != id) {
			return null;
		}
		Optional<User> userOpt = userRepo.findById(id);
		if (!userOpt.isPresent()) {
			return null;
		}
		User dbUser = userOpt.get();
		if (user.getPassword() == null) {
			return null;
		}
		String encodedPW = encoder.encode(user.getPassword());
		dbUser.setPassword(encodedPW);
		return userRepo.saveAndFlush(dbUser);
	}

	//Deleting would create issue with foriegn key relationship // See disabled()
	@Override
	public Boolean delete(String username, int id) {
		userRepo.deleteById(id);
		return ! userRepo.existsById(id);
	}


	@Override
	public Boolean disable(String username, int id) {
		Optional<User> userOpt = userRepo.findById(id);
		if (userOpt.isPresent()) {
			User dbUser = userOpt.get();
			if (dbUser.getUsername().equals(username)) {
				dbUser.setEnabled(false);
				userRepo.saveAndFlush(dbUser);
				return true;
			}
		}
		return false;
	}
	
	@Override
	public Boolean enable(String username, int id) {
		Optional<User> userOpt = userRepo.findById(id);
		if (userOpt.isPresent()) {
			User dbUser = userOpt.get();
			if (dbUser.getUsername().equals(username)) {
				dbUser.setEnabled(true);
				userRepo.saveAndFlush(dbUser);
				return false;
			}
		}
		return false;
	}
}
