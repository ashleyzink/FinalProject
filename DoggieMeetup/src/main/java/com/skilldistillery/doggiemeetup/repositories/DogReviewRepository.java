package com.skilldistillery.doggiemeetup.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.doggiemeetup.entities.DogReview;
import com.skilldistillery.doggiemeetup.entities.DogReviewId;

public interface DogReviewRepository extends JpaRepository<DogReview, DogReviewId> {

}
