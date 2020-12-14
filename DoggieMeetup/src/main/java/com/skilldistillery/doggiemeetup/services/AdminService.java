package com.skilldistillery.doggiemeetup.services;

import com.skilldistillery.doggiemeetup.entities.GeneralComment;
import com.skilldistillery.doggiemeetup.entities.MeetupComment;
import com.skilldistillery.doggiemeetup.entities.User;

public interface AdminService {

//	GeneralComment showGenCom(String username, int genComId);

	User update(User user, int id);

	User showUser(int id);

	GeneralComment findByGeneralCommentId(int genComId);

	boolean destroyGenCom(String username, int genComId);

	MeetupComment findByMeetupCommentId(int meetupCommentId);

	boolean destroyMeetupComments(String username, int meetupComId);

	Boolean disable(int id);

	Boolean enable(int id);

}
