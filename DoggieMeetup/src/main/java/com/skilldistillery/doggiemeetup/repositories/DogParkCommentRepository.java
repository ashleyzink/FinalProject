package com.skilldistillery.doggiemeetup.repositories;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.doggiemeetup.entities.DogParkComment;

public interface DogParkCommentRepository extends JpaRepository<DogParkComment, Integer> {
	List<DogParkComment> findbyUser_Username(String username);
	DogParkComment findByUser_UsernameAndId(String username, int dogParkCommentId);
}
