package com.skilldistillery.doggiemeetup.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.doggiemeetup.entities.DogParkReview;
import com.skilldistillery.doggiemeetup.entities.DogParkReviewId;
import com.skilldistillery.doggiemeetup.entities.User;
import com.skilldistillery.doggiemeetup.repositories.DogParkReviewRepository;
import com.skilldistillery.doggiemeetup.repositories.UserRepository;

@Service
public class DogParkReviewServiceImpl implements DogParkReviewService {

	@Autowired
	private DogParkReviewRepository dogParkReviewRepo;

	@Autowired
	private UserRepository userRepo;

	@Override
	public List<DogParkReview> index() {
		return dogParkReviewRepo.findAll();
	}

	@Override
	public DogParkReview show(int userId, int dogParkId) {
		Optional<DogParkReview> dogParkReviewOpt = dogParkReviewRepo.findById(new DogParkReviewId(userId, dogParkId));
		if (dogParkReviewOpt.isPresent()) {
			return dogParkReviewOpt.get();
		} else {
			return null;
		}
	}

	@Override
	public DogParkReview create(String username, DogParkReview dogParkReview) {
		User user = userRepo.findByUsername(username);
		if (user != null) {
			dogParkReview.setUser(user);
			dogParkReviewRepo.saveAndFlush(dogParkReview);
		}
		return dogParkReview;
	}

	@Override
	public DogParkReview update(String username, int dogParkId, DogParkReview dogParkReview) {
		User user = userRepo.findByUsername(username);
		Optional<DogParkReview> updateDogParkReviewOpt = dogParkReviewRepo
				.findById(new DogParkReviewId(user.getId(), dogParkId));
		DogParkReview managedDogParkReview = null;
		if (updateDogParkReviewOpt.isPresent()) {
			managedDogParkReview = updateDogParkReviewOpt.get();
			if (managedDogParkReview != null) {

			}
			if (dogParkReview.getRating() != null) {
				managedDogParkReview.setRating(dogParkReview.getRating());
			}
			if (dogParkReview.getReview() != null) {
				managedDogParkReview.setReview(dogParkReview.getReview());
			}
			if (dogParkReview.getImgUrl() != null) {
				managedDogParkReview.setImgUrl(dogParkReview.getImgUrl());
			}
			if (dogParkReview.getReviewDate() != null) {
				managedDogParkReview.setReviewDate(dogParkReview.getReviewDate());
			}
			dogParkReviewRepo.saveAndFlush(managedDogParkReview);
		}
		return managedDogParkReview;
	}
 
	@Override
	public boolean destroy(String username, int userId, int dogParkId) {
		boolean deleted = false;
		DogParkReview dogParkReview = dogParkReviewRepo.findByUser_UsernameAndId(username,
				new DogParkReviewId(userId, dogParkId));
		if (dogParkReview != null) {
			dogParkReviewRepo.delete(dogParkReview);
			deleted = true;
		}
		return deleted;
	}

}
