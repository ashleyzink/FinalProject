package com.skilldistillery.doggiemeetup.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.doggiemeetup.entities.DogParkComment;

public interface DogParkCommentRepository extends JpaRepository<DogParkComment, Integer> {

}
