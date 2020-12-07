package com.skilldistillery.doggiemeetup.services;

import java.util.List;

import com.skilldistillery.doggiemeetup.entities.MeetupComment;

public interface MeetupCommentService {

//	public List<MeetupComment> index(int userId);

	public List<MeetupComment> getAllMeetupComments();

	public MeetupComment show(int meetupId, int meetupComId);

	public MeetupComment create(String username, MeetupComment meetupCom);

	public MeetupComment update(String username, int meetupComId, MeetupComment meetupCom);

	public boolean destroy(String username, int meetupComId);

	MeetupComment findByMeetupCommentId(int meetupCommentId);

}
