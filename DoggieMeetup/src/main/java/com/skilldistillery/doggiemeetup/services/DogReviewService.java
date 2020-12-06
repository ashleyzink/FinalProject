package com.skilldistillery.doggiemeetup.services;

import java.util.List;

import com.skilldistillery.doggiemeetup.entities.DogReview;

public interface DogReviewService {
	public List<DogReview> index();
	public DogReview show(int userId, int dogReviewId);
	public DogReview create(String username, DogReview dogReview);
	public DogReview update(String username, int dogReviewId, DogReview dogReview);
	public boolean destory(String username, int dogReviewId);

}
