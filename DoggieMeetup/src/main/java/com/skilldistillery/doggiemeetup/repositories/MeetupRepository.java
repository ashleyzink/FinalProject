package com.skilldistillery.doggiemeetup.repositories;



import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.doggiemeetup.entities.Meetup;

public interface MeetupRepository extends JpaRepository<Meetup, Integer> {
	

}
