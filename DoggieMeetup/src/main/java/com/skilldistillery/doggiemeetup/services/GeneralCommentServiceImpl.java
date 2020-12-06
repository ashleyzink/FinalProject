package com.skilldistillery.doggiemeetup.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.doggiemeetup.entities.Dog;
import com.skilldistillery.doggiemeetup.entities.GeneralComment;
import com.skilldistillery.doggiemeetup.entities.User;
import com.skilldistillery.doggiemeetup.repositories.GeneralCommentRepository;
import com.skilldistillery.doggiemeetup.repositories.UserRepository;

@Service
public class GeneralCommentServiceImpl implements GeneralCommentService {

	@Autowired
	private GeneralCommentRepository genComRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Override
	public List<GeneralComment> index(String username) {
		if (userRepo.findByUsername(username) == null) {
			return null;
		}
		return genComRepo.findbyUser_Username(username);
	}

	@Override
	public List<GeneralComment> getAllGeneralComments() {
		return genComRepo.findAll();
	}

	@Override
	public GeneralComment show(String username, int genComId) {
		return genComRepo.findByUser_UsernameAndId(username, genComId);
	}

	@Override
	public GeneralComment create(String username, GeneralComment genComment) {
		User user = userRepo.findByUsername(username);
		if (user != null) {
			genComment.setUser(user);
			genComRepo.saveAndFlush(genComment);
		}
		return genComment;
	}

	@Override
	public GeneralComment update(String username, int genComId, GeneralComment genComment) {
		GeneralComment managedGenCom = genComRepo.findByUser_UsernameAndId(username, genComId);
		if (managedGenCom != null) {
			managedGenCom.setCommentText(genComment.getCommentText());
			managedGenCom.setTitle(genComment.getTitle());
			managedGenCom.setReplyToComment(genComment.getReplyToComment());
			genComRepo.saveAndFlush(managedGenCom);
		}
		return managedGenCom;
	}

	@Override
	public boolean destroy(String username, int genComId) {
		boolean deleted = false;
		GeneralComment genComment = genComRepo.findByUser_UsernameAndId(username, genComId);
		if (genComment != null) {
			genComRepo.delete(genComment);
			deleted = true;
		}
		return deleted;
	}

}
