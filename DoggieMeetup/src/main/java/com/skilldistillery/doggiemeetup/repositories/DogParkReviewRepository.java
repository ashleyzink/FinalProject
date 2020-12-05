package com.skilldistillery.doggiemeetup.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.doggiemeetup.entities.DogParkReview;
import com.skilldistillery.doggiemeetup.entities.DogParkReviewId;

public interface DogParkReviewRepository extends JpaRepository<DogParkReview, DogParkReviewId> {

}
