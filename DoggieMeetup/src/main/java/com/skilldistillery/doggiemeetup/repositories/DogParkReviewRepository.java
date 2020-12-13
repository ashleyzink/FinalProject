package com.skilldistillery.doggiemeetup.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.doggiemeetup.entities.DogParkReview;
import com.skilldistillery.doggiemeetup.entities.DogParkReviewId;

public interface DogParkReviewRepository extends JpaRepository<DogParkReview, DogParkReviewId> {
	DogParkReview findByUser_UsernameAndId(String username, DogParkReviewId dogParkReviewId);
	List<DogParkReview> findByDogPark_Id(int dogParkId);
}

