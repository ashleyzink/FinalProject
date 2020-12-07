package com.skilldistillery.doggiemeetup.services;

import java.util.List;

import com.skilldistillery.doggiemeetup.entities.GeneralComment;

public interface GeneralCommentService {
	
		//For User profile
		public List<GeneralComment> index(String username);

		//To search for all comments regardless of user
		public List<GeneralComment> getAllGeneralComments();
		
		public GeneralComment show(String username, int genComId);
		
		public GeneralComment create(String username, GeneralComment genComment);
		
		public GeneralComment update(String username, int genComId, GeneralComment genComment);
		
		public boolean destroy(String username, int genComId);

		GeneralComment findByGeneralCommentId(int genComId);

}
