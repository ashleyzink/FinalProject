package com.skilldistillery.doggiemeetup.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.skilldistillery.doggiemeetup.entities.GeneralComment;
import com.skilldistillery.doggiemeetup.entities.MeetupComment;
import com.skilldistillery.doggiemeetup.entities.User;
import com.skilldistillery.doggiemeetup.repositories.DogParkCommentRepository;
import com.skilldistillery.doggiemeetup.repositories.DogParkRepository;
import com.skilldistillery.doggiemeetup.repositories.DogRepository;
import com.skilldistillery.doggiemeetup.repositories.GeneralCommentRepository;
import com.skilldistillery.doggiemeetup.repositories.MeetupCommentRepository;
import com.skilldistillery.doggiemeetup.repositories.MeetupRepository;
import com.skilldistillery.doggiemeetup.repositories.UserRepository;

@Repository
@Transactional
public class AdminServiceImpl implements AdminService {

	// List of Autowired repositories
	@Autowired
	private UserRepository userRepo;

	@Autowired
	DogParkRepository dogParkRepo;

	@Autowired
	DogParkCommentRepository dogParkCommentRepo;

	@Autowired
	DogRepository dogRepo;

	@Autowired
	MeetupCommentRepository meetupComRepo;

	@Autowired
	MeetupRepository meetupRepo;

	@Autowired
	GeneralCommentRepository genComRepo;

	@Autowired
	private PasswordEncoder encoder;

	// Override methods for admin priviledges over user

	@Override
	public User showUser(int id) {
		Optional<User> userOpt = userRepo.findById(id);
		if (!userOpt.isPresent()) {

		}
		return userOpt.get();
	}

	@Override
	public User update(User user, int id) {
		if (user.getId() != id) {
			return null;
		}
		Optional<User> userOpt = userRepo.findById(id);
		if (!userOpt.isPresent()) {
			return null;
		}
		User dbUser = userOpt.get();

		if (user.getDateOption() != null) {
			dbUser.setDateOption(user.getDateOption());
		}
		if (user.getFirstName() != null) {
			dbUser.setFirstName(user.getFirstName());
		}
		if (user.getLastName() != null) {
			dbUser.setLastName(user.getLastName());
		}
		if (user.getUsername() != null) {
			dbUser.setUsername(user.getUsername());
		}
		if (user.getEmail() != null) {
			dbUser.setEmail(user.getEmail());
		}
		if (user.getRole() != null) {
			dbUser.setRole(user.getRole());
		}
		if (user.getEnabled() != null) {
			dbUser.setEnabled(user.getEnabled());
		}
		if (user.getProfilePhotoUrl() != null) {
			dbUser.setProfilePhotoUrl(user.getProfilePhotoUrl());
		}
		if (user.getBio() != null) {
			dbUser.setBio(user.getBio());
		}
		if (user.getProfilePrivate() != null) {
			dbUser.setProfilePrivate(user.getProfilePrivate());
		}
		if (user.getLocationPrivate() != null) {
			dbUser.setLocationPrivate(user.getLocationPrivate());
		}
		return userRepo.saveAndFlush(dbUser);
	}

	// Gencom implementations

	@Override
	public GeneralComment findByGeneralCommentId(int genComId) {
		Optional<GeneralComment> genCommentOpt = genComRepo.findById(genComId);
		if (!genCommentOpt.isPresent()) {
			return null;
		}

		return genCommentOpt.get();
	}

	@Override
	public boolean destroyGenCom(String username, int genComId) {
		boolean deleted = false;
		GeneralComment genComment = genComRepo.findByUser_UsernameAndId(username, genComId);
		if (genComment != null) {
			genComRepo.delete(genComment);
			deleted = true;
		}
		return deleted;
	}

	// MeetupCom implementations
	@Override
	public MeetupComment findByMeetupCommentId(int meetupCommentId) {
		Optional<MeetupComment> meetupCommentOpt = meetupComRepo.findById(meetupCommentId);
		if (!meetupCommentOpt.isPresent()) {
			return null;
		}
		return meetupCommentOpt.get();
	}

	@Override
	public boolean destroyMeetupComments(String username, int meetupComId) {
		boolean deleted = false;
		MeetupComment meetupComment = meetupComRepo.findByUser_UsernameAndId(username, meetupComId);
		if (meetupComment != null) {
			meetupComRepo.delete(meetupComment);
			deleted = true;
		}
		return deleted;
	}

}
