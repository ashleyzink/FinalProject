package com.skilldistillery.doggiemeetup.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.doggiemeetup.entities.Meetup;
import com.skilldistillery.doggiemeetup.repositories.MeetupRepository;
import com.skilldistillery.doggiemeetup.repositories.UserRepository;

@Service
public class MeetupServiceImpl implements MeetupService {
	
	@Autowired
	private MeetupRepository meetupRepo;
	@Autowired
	private UserRepository userRepo;

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
	public Meetup create(Meetup meetup) {
		return meetupRepo.saveAndFlush(meetup);
	}

	@Override
	public Meetup update(Meetup meetup, int id) {
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
	public Boolean delete(int id) {
		meetupRepo.deleteById(id);
		return ! meetupRepo.existsById(id);
	}

}
