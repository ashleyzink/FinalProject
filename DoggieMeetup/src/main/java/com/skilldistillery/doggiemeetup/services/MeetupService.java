package com.skilldistillery.doggiemeetup.services;

import java.util.List;

import com.skilldistillery.doggiemeetup.entities.Meetup;

public interface MeetupService {
	
	List<Meetup> index();
	
	Meetup show(int id);
	
	Meetup create(String username, Meetup meetup);
	
	Meetup update(String username, Meetup meetup, int id);
	
	Boolean delete(String username, int id);

}
