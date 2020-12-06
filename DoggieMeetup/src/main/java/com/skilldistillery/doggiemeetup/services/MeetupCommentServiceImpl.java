package com.skilldistillery.doggiemeetup.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.doggiemeetup.entities.MeetupComment;
import com.skilldistillery.doggiemeetup.entities.User;
import com.skilldistillery.doggiemeetup.repositories.MeetupCommentRepository;
import com.skilldistillery.doggiemeetup.repositories.UserRepository;


@Service
public class MeetupCommentServiceImpl implements MeetupCommentService {
	
	@Autowired
	private MeetupCommentRepository meetupComRepo;
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public List<MeetupComment> index(String username) {
		if(userRepo.findByUsername(username) == null) {
			return null;
		}
		return meetupComRepo.findbyUser_Username(username);
	}

	@Override
	public List<MeetupComment> getAllMeetupComments() {
		return meetupComRepo.findAll();
	}

	@Override
	public MeetupComment show(String username, int meetupComId) {
		return meetupComRepo.findByUser_UsernameAndId(username, meetupComId);
	}

	@Override
	public MeetupComment create(String username, MeetupComment meetupCom) {
		User user = userRepo.findByUsername(username);
		if(user != null) {
			meetupCom.setUser(user);
			meetupComRepo.saveAndFlush(meetupCom);
		}
		return meetupCom;
	}

	@Override
	public MeetupComment update(String username, int meetupComId, MeetupComment meetupCom) {
		MeetupComment managedMeetupComment = meetupComRepo.findByUser_UsernameAndId(username, meetupComId);
		if(managedMeetupComment != null) {
			managedMeetupComment.setCommentText(meetupCom.getCommentText());
			managedMeetupComment.setTitle(meetupCom.getTitle());
			managedMeetupComment.setReplyToComment(meetupCom.getReplyToComment());
			meetupComRepo.saveAndFlush(managedMeetupComment);
			
		}
		return managedMeetupComment;
	}

	@Override
	public boolean destroy(String username, int meetupComId) {
		boolean deleted = false;
		MeetupComment meetupCom = meetupComRepo.findByUser_UsernameAndId(username, meetupComId);
		if (meetupCom != null) {
			meetupComRepo.delete(meetupCom);
			deleted = true;
		}
		return deleted;
	}

	
}
