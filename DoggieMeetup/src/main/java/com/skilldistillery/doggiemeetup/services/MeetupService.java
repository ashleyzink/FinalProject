package com.skilldistillery.doggiemeetup.services;

import java.util.List;

import com.skilldistillery.doggiemeetup.entities.Meetup;

public interface MeetupService {
	
	List<Meetup> index();
	
	Meetup show(int id);
	
	Meetup create(Meetup meetup);
	
	Meetup update(Meetup meetup, int id);
	
	Boolean delete(int id);

}
