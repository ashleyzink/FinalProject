package com.skilldistillery.doggiemeetup.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.doggiemeetup.entities.Dog;
import com.skilldistillery.doggiemeetup.entities.Meetup;
import com.skilldistillery.doggiemeetup.entities.User;
import com.skilldistillery.doggiemeetup.repositories.DogRepository;
import com.skilldistillery.doggiemeetup.repositories.MeetupRepository;
import com.skilldistillery.doggiemeetup.repositories.UserRepository;

@Service
public class MeetupServiceImpl implements MeetupService {
	
	@Autowired
	private MeetupRepository meetupRepo;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private DogRepository dogRepo;

	@Override
	public List<Meetup> index() {
		return meetupRepo.findAll();
	}

	@Override
	public Meetup show(int id) {
		Optional<Meetup> meetupOpt = meetupRepo.findById(id);
		if (!meetupOpt.isPresent()) {
			return null;
		}
		return meetupOpt.get();
	}

	@Override
	public Meetup create(String username, Meetup meetup) {
		User user = userRepo.findByUsername(username);
		if(user != null) {
			meetup.setUser(user);
			meetupRepo.saveAndFlush(meetup);
		}
		return meetup;
	}

	@Override
	public Meetup update(String username, Meetup meetup, int id) {
		if(userRepo.findByUsername(username) == null) {
			return null;
		}
		Optional<Meetup> meetupOpt = meetupRepo.findById(id);
		Meetup dbMeetup = null;
		if (meetupOpt.isPresent() && meetupOpt.get().getId() == id) {
			dbMeetup = meetupOpt.get();
			if (dbMeetup == null) {
				return null;
			}
		}
		if (meetup.getDescription() != null) { dbMeetup.setDescription(meetup.getDescription()); }
		if (meetup.getTitle() != null) { dbMeetup.setTitle(meetup.getTitle()); }
		if (meetup.getUser() != null) { dbMeetup.setUser(meetup.getUser()); }
		if (meetup.getMeetupDate() != null) { dbMeetup.setMeetupDate(meetup.getMeetupDate()); }
		if (meetup.getDogPark() != null) { dbMeetup.setDogPark(meetup.getDogPark()); }
		if (meetup.getDogs() != null) { dbMeetup.setDogs(meetup.getDogs()); }
		
		return meetupRepo.saveAndFlush(dbMeetup);
	}

	@Override
	public Boolean delete(String username, int id) {
		User user = userRepo.findByUsername(username);
		meetupRepo.deleteById(id);
		return ! meetupRepo.existsById(id);
			
	}

	@Override
	public List<Meetup> indexByDogParkId(int dogParkId) {
		return meetupRepo.findByDogPark_id(dogParkId);
	}

	@Override
	public List<Dog> getDogsByMeetup(int meetupId) {
		return dogRepo.findByMeetups_Id(meetupId);
	}

}
