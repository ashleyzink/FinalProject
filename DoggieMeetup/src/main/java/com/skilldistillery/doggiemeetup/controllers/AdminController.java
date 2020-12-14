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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.doggiemeetup.entities.GeneralComment;
import com.skilldistillery.doggiemeetup.entities.MeetupComment;
import com.skilldistillery.doggiemeetup.entities.User;
import com.skilldistillery.doggiemeetup.services.AdminService;
import com.skilldistillery.doggiemeetup.services.GeneralCommentService;
import com.skilldistillery.doggiemeetup.services.UserService;

@CrossOrigin({ "*", "http://localhost:4201" })
@RequestMapping("api")
@RestController
public class AdminController {

	@Autowired
	private UserService userSvc;

	@Autowired
	private AdminService adminSvc;

	@Autowired
	private GeneralCommentService gencomSvc;

	// Admin mapping for User
	@GetMapping("auth/admin/users/{userId}")
	public User show(HttpServletRequest req, HttpServletResponse res, @PathVariable int userId, Principal principal) {
		User user = adminSvc.showUser(userId);
		if (user == null) {
			res.setStatus(404);
		}
		return user;
	}
	
	@GetMapping("admin/search/{keyword}")
	public List<User> getUserFromKeyword(@PathVariable String keyword) {
		return adminSvc.getUsernameEmailFirstNameLastNameBioByKeyword(keyword);
	}

	@PutMapping("auth/admin/users/{userId}")
	public User update(HttpServletRequest req, HttpServletResponse res, @PathVariable int userId,
			@RequestBody User user, Principal principal) {

		if (hasAdminRole(principal)) {
			try {
				user = adminSvc.update(user, userId);
				res.setStatus(200);
				if (user == null) {
					res.setStatus(404);
				}
			} catch (Exception e) {
				res.setStatus(400);
				user = null;
			}
			return user;
		} else {
			res.setStatus(401);
			return null;
		}
	}

	private boolean hasAdminRole(Principal principal) {
		return userSvc.show(principal.getName()).getRole().equals("admin");
	}

	@PutMapping("auth/admin/users/{userId}/enable")
	public void enable(HttpServletRequest req, HttpServletResponse res, @PathVariable int userId, Principal principal) {
		try {
			System.out.println("*********************" + principal + "************************");
			if (hasAdminRole(principal)) {
				System.out.println("*********************" + principal + "************************");
				boolean enabled = adminSvc.enable(userId);
				if (enabled) {
					res.setStatus(200);
				} else {
					res.setStatus(404);
				}
			}
		} catch (Exception e) {
			res.setStatus(400);
		}
	}

	@PutMapping("auth/admin/users/rejoin/{userId}")
	public void enable2(HttpServletRequest req, HttpServletResponse res, @PathVariable int userId,
			Principal principal) {
		try {
			System.out.println("*********************" + principal + "************************");
			if (hasAdminRole(principal)) {
				System.out.println("*********************" + principal + "************************");
				boolean enabled = adminSvc.enable(userId);
				if (enabled) {
					res.setStatus(200);
				} else {
					res.setStatus(404);
				}
			}
		} catch (Exception e) {
			res.setStatus(400);
		}
	}

	@DeleteMapping("auth/admin/users/{userId}")
	public void disable(HttpServletRequest req, HttpServletResponse res, @PathVariable int userId,
			Principal principal) {
		try {
			if (hasAdminRole(principal)) {
				boolean disabled = adminSvc.disable(userId);
				if (disabled) {
					res.setStatus(204);
				} else {
					res.setStatus(404);
				}
			}
		} catch (Exception e) {
			res.setStatus(400);
		}
	}

	@DeleteMapping("auth/admin/dogs/{dogId}")
	public void destroyDog(HttpServletRequest req, HttpServletResponse res, @PathVariable int dogId,
			Principal principal) {
		try {
			if (hasAdminRole(principal)) {
				boolean deleted = adminSvc.destroyDog(dogId);
				if (deleted) {
					res.setStatus(204);
				} else {
					res.setStatus(404);
				}
			}
		} catch (Exception e) {
			res.setStatus(400);
		}

	}

	// Admin mapping for General Comments
	@GetMapping("auth/admin/generalComments/{genComId}")
	public GeneralComment show(HttpServletResponse res, HttpServletRequest req, @PathVariable int genComId) {
		GeneralComment generalComments = adminSvc.findByGeneralCommentId(genComId);
		if (generalComments == null) {
			res.setStatus(404);
		}
		return generalComments;
	}

	@DeleteMapping("auth/admin/generalComments/{genComId}")
	public void destroy(HttpServletRequest req, HttpServletResponse res, @PathVariable int genComId,
			Principal principal) {
		if (hasAdminRole(principal)) {
			try {
				boolean deleted = adminSvc.destroyGenCom(principal.getName(), genComId);
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

	// Admin mapping for Meetup Comments
	@GetMapping("auth/admin/meetups/{meetupId}/meetupComments/{meetupCommentId}")
	public MeetupComment show(HttpServletRequest req, HttpServletResponse res, @PathVariable int meetupId,
			@PathVariable int meetupCommentId) {
		MeetupComment meetupComments = adminSvc.findByMeetupCommentId(meetupCommentId);
		if (meetupComments == null) {
			res.setStatus(404);
		}
		return meetupComments;
	}

	@DeleteMapping("auth/admin/meetups/{meetupId}/meetupComments/{meetupCommentId}")
	public void destroyMeetupCom(HttpServletRequest req, HttpServletResponse res, @PathVariable int meetupCommentId,
			Principal principal) {
		if (hasAdminRole(principal)) {
			try {
				boolean deleted = adminSvc.destroyMeetupComments(principal.getName(), meetupCommentId);
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

}
