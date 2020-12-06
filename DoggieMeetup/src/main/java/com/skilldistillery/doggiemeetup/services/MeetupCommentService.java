package com.skilldistillery.doggiemeetup.services;

import java.util.List;

import com.skilldistillery.doggiemeetup.entities.MeetupComment;

public interface MeetupCommentService {
	
	//For User profile
		public List<MeetupComment> index(String username);
		
		//To search for all comments regardless of user
		public List<MeetupComment> getAllMeetupComments(); 
		
		public MeetupComment show(String username, int meetupComId);
		
		public MeetupComment create(String username, MeetupComment meetupCom);
		
		public MeetupComment update(String username, int meetupComId, MeetupComment meetupCom);
		
		public boolean destroy(String username, int meetupComId);

}
