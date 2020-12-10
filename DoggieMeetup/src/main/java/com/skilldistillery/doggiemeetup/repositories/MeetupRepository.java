package com.skilldistillery.doggiemeetup.repositories;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.doggiemeetup.entities.Meetup;

public interface MeetupRepository extends JpaRepository<Meetup, Integer> {
	
	List<Meetup> findByDogPark_id(int dogParkId);
}
