package com.skilldistillery.doggiemeetup.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.doggiemeetup.entities.DogReview;
import com.skilldistillery.doggiemeetup.entities.DogReviewId;
import com.skilldistillery.doggiemeetup.entities.User;
import com.skilldistillery.doggiemeetup.repositories.DogReviewRepository;
import com.skilldistillery.doggiemeetup.repositories.UserRepository;
@Service
public class DogReviewServiceImpl implements DogReviewService {
	
	@Autowired
	private DogReviewRepository dogReviewRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Override
	public List<DogReview> index() {
		return dogReviewRepo.findAll();
	}

	@Override
	public DogReview show(int userId, int dogReviewId) {
		Optional<DogReview> dogReviewOpt = dogReviewRepo.findById(new DogReviewId(userId, dogReviewId));
		if(dogReviewOpt.isPresent()) {
			return dogReviewOpt.get();
		}else {
			return null;
		}
	}
			

	@Override
	public DogReview create(String username, DogReview dogReview) {
		User user = userRepo.findByUsername(username);
		if(user != null) {
			dogReview.setUser(user);
			dogReviewRepo.saveAndFlush(dogReview);
		}
		return dogReview;
	}

	@Override
	public DogReview update(String username, int dogReviewId, DogReview dogReview) {
		User user = userRepo.findByUsername(username);
		Optional<DogReview> updateDogReviewOpt = dogReviewRepo.findById(new DogReviewId(user.getId(), dogReviewId));
		DogReview managedDogReview = null;
		if(updateDogReviewOpt.isPresent()) {
			managedDogReview = updateDogReviewOpt.get();
			if(managedDogReview != null) {
			}
			if(dogReview.getRating() != null) {
				managedDogReview.setRating(dogReview.getRating());
			}
			if(dogReview.getReview() != null) {
				managedDogReview.setReview(dogReview.getReview());
			}
			if(dogReview.getImgUrl() != null) {
				managedDogReview.setImgUrl(dogReview.getImgUrl());
			}
			if(dogReview.getReviewDate() != null) {
				managedDogReview.setReviewDate(dogReview.getReviewDate());
			}
			dogReviewRepo.saveAndFlush(managedDogReview);
		}
		return managedDogReview;
	}

	@Override
	public boolean destory(String username, int dogReviewId) {
		boolean deleted = false;
		DogReview dogReview = dogReviewRepo.findByUser_UsernameAndId(username, dogReviewId);
		if(dogReview != null) {
			dogReviewRepo.delete(dogReview);
			deleted = true;
		}
		return deleted;
	}

}
