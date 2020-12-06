package com.skilldistillery.doggiemeetup.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.doggiemeetup.entities.DogParkReview;
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
		return null;
	}

	@Override
	public DogParkReview show(int dogParkId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DogParkReview create(String username, DogParkReview dogParkReview) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DogParkReview update(String username, int dogParkId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean destroy(String username, int dogParkId) {
		// TODO Auto-generated method stub
		return false;
	}

	
}
