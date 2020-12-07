package com.skilldistillery.doggiemeetup.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.doggiemeetup.entities.MeetupComment;
import com.skilldistillery.doggiemeetup.services.MeetupCommentService;
//import com.skilldistillery.doggiemeetup.services.UserService;

@CrossOrigin({ "*", "http://localhost:4210" })
@RequestMapping("api")
@RestController
public class MeetupCommentController {

//	@Autowired
//	private UserService uSvc;
	@Autowired
	private MeetupCommentService mcs;

//	@GetMapping("meetupComments/userId")
//	public List<MeetupComment> index(HttpServletResponse res, @PathVariable int userId) {
//		List<MeetupComment> meetupCommentsForUser = mcs.index(userId);
//		if (meetupCommentsForUser == null) {
//			res.setStatus(404);
//		}
//		return meetupCommentsForUser;
//	}

	@GetMapping("meetupComments")
	public List<MeetupComment> lists() {
		return mcs.getAllMeetupComments();
	}

	@GetMapping("meetups/{meetupId}/meetupComments/{meetupCommentId}")
	public MeetupComment show(HttpServletRequest req, HttpServletResponse res, @PathVariable int meetupId,
			@PathVariable int meetupCommentId) {
		MeetupComment meetupComment = mcs.findByMeetupCommentId(meetupCommentId);
		if (meetupComment == null) {
			res.setStatus(404);
		}
		return meetupComment;
	}

	@PostMapping("auth/meetups/{meetupId}/meetupComments")
	public MeetupComment create(HttpServletRequest req, HttpServletResponse res,
			@RequestBody MeetupComment meetupComment, Principal principal) {

		try {
			meetupComment = mcs.create(principal.getName(), meetupComment);
			res.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/").append(meetupComment.getId());
			res.setHeader("Location", url.toString());
		} catch (Exception e) {
			res.setStatus(400);
			meetupComment = null;
		}
		return meetupComment;
	}

	@PutMapping("auth/meetups/{meetupId}/meetupComments/{meetupCommentId}")
	public MeetupComment update(HttpServletRequest req, HttpServletResponse res, @PathVariable int meetupCommentId,
			@RequestBody MeetupComment meetupComment, Principal principal) {
		try {
			meetupComment = mcs.update(principal.getName(), meetupCommentId, meetupComment);
			if (meetupComment == null) {
				res.setStatus(404);
			}

		} catch (Exception e) {
			res.setStatus(400);
			meetupComment = null;
		}
		return meetupComment;
	}

	@DeleteMapping("auth/meetups/{meetupId}/meetupComments/{meetupCommentId}")
	public void destroy(HttpServletRequest req, HttpServletResponse res, @PathVariable int meetupCommentId,
			Principal principal) {
		try {
			boolean deleted = mcs.destroy(principal.getName(), meetupCommentId);
			if (deleted) {
				res.setStatus(204);
			} else {
				res.setStatus(404);
			}
		} catch (Exception e) {
			res.setStatus(400);
		}
	}

}
