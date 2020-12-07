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
import com.skilldistillery.doggiemeetup.services.UserService;

@CrossOrigin({ "*", "http://localhost:4210" })
@RequestMapping("api")
@RestController
public class MeetupCommentController {

	@Autowired
	private UserService uSvc;
	@Autowired
	private MeetupCommentService mcs;

	@GetMapping("meetups/{meetupId}/meetupComments/{meetupCommentId}")
	public MeetupComment show(HttpServletRequest req, HttpServletResponse res, @PathVariable int meetupId,
			@PathVariable int meetupCommentId) {
		MeetupComment meetupComment = mcs.show(meetupId, meetupCommentId);
		if (meetupComment == null) {
			res.setStatus(404);
		}
		return meetupComment;
	}

	@GetMapping("meetupComments/search/{keyword}")
	public List<MeetupComment> findByMeetupCommentLike(HttpServletRequest req, HttpServletResponse res,
			@PathVariable String commentText) {
		return mcs.findByMeetupCommentLike(commentText);

	}

//	@GetMapping("meetupComments/search/{keyword}")
	// List <MeetupComment>

	@PostMapping("auth/meetups/{meetupId}/meetupComments")
	public MeetupComment create(HttpServletRequest req, HttpServletResponse res, @PathVariable String username,
			@RequestBody MeetupComment meetupComment, Principal principal) {

		try {
			meetupComment = mcs.create(username, meetupComment);
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
	public MeetupComment update(HttpServletRequest req, HttpServletResponse res, @PathVariable String username,
			@PathVariable int meetupCommentId, @RequestBody MeetupComment meetupComment, Principal principal) {
		try {
			meetupComment = mcs.update(username, meetupCommentId, meetupComment);
			if (meetupComment == null) {
				res.setStatus(404);
			}

		} catch (Exception e) {
			res.setStatus(400);
			meetupComment = null;
		}
		return meetupComment;
	}

	@DeleteMapping("auth/meetups/{meetupId}/meetupComments}")
	public void destroy(HttpServletRequest req, HttpServletResponse res, @PathVariable String username,
			@PathVariable int meetupCommentId, Principal principal) {
		try {
			boolean deleted = mcs.destroy(username, meetupCommentId);
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
