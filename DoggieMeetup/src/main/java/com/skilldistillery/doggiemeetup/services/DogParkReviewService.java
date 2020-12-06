package com.skilldistillery.doggiemeetup.services;

import java.util.List;

import com.skilldistillery.doggiemeetup.entities.DogParkReview;

public interface DogParkReviewService {
	
	public List<DogParkReview> index();
	public DogParkReview show(int dogParkId);
	public DogParkReview create(String username, DogParkReview dogParkReview);
	public DogParkReview update(String username, int dogParkId);
	public boolean destroy(String username, int dogParkId);

}
