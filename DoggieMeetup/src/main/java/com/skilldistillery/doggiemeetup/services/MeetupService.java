package com.skilldistillery.doggiemeetup.services;

import java.util.List;

import com.skilldistillery.doggiemeetup.entities.Dog;
import com.skilldistillery.doggiemeetup.entities.Meetup;

public interface MeetupService {
	
	List<Meetup> index();
	
	List<Meetup> indexByDogParkId(int dogParkId);
	
	Meetup show(int id);
	
	Meetup create(String username, Meetup meetup);
	
	Meetup update(String username, Meetup meetup, int id);
	
	Boolean delete(String username, int id);
	
	List<Dog> getDogsByMeetup(int meetupId);

}
