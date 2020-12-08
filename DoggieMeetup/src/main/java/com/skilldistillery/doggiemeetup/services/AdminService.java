package com.skilldistillery.doggiemeetup.services;

import com.skilldistillery.doggiemeetup.entities.GeneralComment;
import com.skilldistillery.doggiemeetup.entities.User;

public interface AdminService {

//	GeneralComment showGenCom(String username, int genComId);

	User update(User user, int id);

	User showUser(int id);

	GeneralComment findByGeneralCommentId(int genComId);

	boolean destroy(String username, int genComId);

}
