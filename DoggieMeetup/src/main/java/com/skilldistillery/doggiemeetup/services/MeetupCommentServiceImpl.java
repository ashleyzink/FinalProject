package com.skilldistillery.doggiemeetup.services;

import java.util.List;
import java.util.Optional;

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
	public List<MeetupComment> getAllMeetupComments() {
		return meetupComRepo.findAll();
	}

	@Override
	public MeetupComment create(String username, MeetupComment meetupCom) {
		User user = userRepo.findByUsername(username);
		if (user != null) {
			meetupCom.setUser(user);
			meetupComRepo.saveAndFlush(meetupCom);
		}
		return meetupCom;
	}

	@Override
	public MeetupComment update(String username, int meetupComId, MeetupComment meetupCom) {
		MeetupComment managedMeetupComment = meetupComRepo.findByUser_UsernameAndId(username, meetupComId);
		if (managedMeetupComment != null) {
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

	@Override
	public List<MeetupComment> findByMeetupId(int id) {
		return meetupComRepo.findByMeetup_Id(id);
	}

	@Override
	public MeetupComment show(int meetupId, int meetupComId) {
		Optional<MeetupComment> meetupOpt = meetupComRepo.findById(meetupComId);
		MeetupComment meetupComment = null;
		if (meetupOpt.isPresent()) {
			meetupComment = meetupOpt.get();
		} else {
			return null;
		}
		if (meetupId == meetupComment.getMeetup().getId()) {
			return meetupComment;
		} else {
			return null;
		}

	}

	@Override
	public List<MeetupComment> findByMeetupCommentLike(String commentText) {
		String meetupComContaining = "%" + commentText + "%";
		return meetupComRepo.findByMeetup__Id_CommentLike(meetupComContaining);
	}

}
