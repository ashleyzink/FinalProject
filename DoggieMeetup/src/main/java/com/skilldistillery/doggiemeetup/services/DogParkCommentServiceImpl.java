package com.skilldistillery.doggiemeetup.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.doggiemeetup.entities.DogParkComment;
import com.skilldistillery.doggiemeetup.entities.User;
import com.skilldistillery.doggiemeetup.repositories.DogParkCommentRepository;
import com.skilldistillery.doggiemeetup.repositories.UserRepository;

@Service
public class DogParkCommentServiceImpl implements DogParkCommentService {

	@Autowired
	private DogParkCommentRepository dogParkCommentRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Override
	public List<DogParkComment> index(String username) {
		if(userRepo.findByUsername(username) == null) {
			return null;
		}
		return dogParkCommentRepo.findByUser_Username(username);
	}
	@Override
	public List<DogParkComment> getCommentsByUserId(int userId) {
		
		return dogParkCommentRepo.findByUser_Id(userId);
	}

	@Override
	public List<DogParkComment> getAllDogParkComments() {
		return dogParkCommentRepo.findAll();
	}

	@Override
	public DogParkComment show(int dogParkCommentId) {
		Optional<DogParkComment> dogParkCommentOpt = dogParkCommentRepo.findById(dogParkCommentId);
		if(! dogParkCommentOpt.isPresent()) {
			return null;
		}
		
		return dogParkCommentOpt.get();
	}

	@Override
	public DogParkComment create(String username, DogParkComment dogParkComment) {
		User user = userRepo.findByUsername(username);
		if(user != null) {
			dogParkComment.setUser(user);
			dogParkCommentRepo.saveAndFlush(dogParkComment);
		}
		return dogParkComment;
	}

	@Override
	public DogParkComment update(String username, int dogParkCommentId, DogParkComment dogParkComment) {
		DogParkComment managedDogParkComment = dogParkCommentRepo.findByUser_UsernameAndId(username, dogParkCommentId);
		if(managedDogParkComment != null) {
			managedDogParkComment.setCommentText(dogParkComment.getCommentText());
			managedDogParkComment.setTitle(dogParkComment.getTitle());
			managedDogParkComment.setReplyToComment(dogParkComment.getReplyToComment());
			dogParkCommentRepo.saveAndFlush(managedDogParkComment);
		}
		return managedDogParkComment;
	}

	@Override
	public boolean destroy(String username, int dogParkCommentId) {
		boolean deleted = false;
		DogParkComment dogParkComment = dogParkCommentRepo.findByUser_UsernameAndId(username, dogParkCommentId);
		if(dogParkComment != null) {
			dogParkCommentRepo.delete(dogParkComment);
			deleted = true;
		}
		return deleted;
	}

}
