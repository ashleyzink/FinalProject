package com.skilldistillery.doggiemeetup.services;

import java.util.List;

import com.skilldistillery.doggiemeetup.entities.DogParkReview;

public interface DogParkReviewService {
	
	public List<DogParkReview> index();
	public List<DogParkReview> index(int dogParkId);
	public DogParkReview show(int userId, int dogParkId);
	public DogParkReview update(String username, int dogParkId, DogParkReview dogParkReview);
	boolean destroy(String username, int userId, int dogParkId);
	DogParkReview create(String username, DogParkReview dogParkReview, int dogParkId);

}
