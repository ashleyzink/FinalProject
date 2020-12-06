package com.skilldistillery.doggiemeetup.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.doggiemeetup.entities.Dog;

public interface DogRepository extends JpaRepository<Dog, Integer> {
	
	List<Dog> findByUser_Username(String username);
	
	Dog findByUser_UsernameAndId(String username, int dogId);

}
