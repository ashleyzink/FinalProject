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

import com.skilldistillery.doggiemeetup.entities.User;
import com.skilldistillery.doggiemeetup.services.UserService;

@CrossOrigin({"*", "http://localhost:4201"})
@RequestMapping("api")
@RestController
public class ProfileController {
	
	@Autowired
	private UserService userSvc;
	
	
	@GetMapping("users")
	private List<User> index() {
		return userSvc.index();
	}
	
	@GetMapping("users/{userId}")
	public User show(HttpServletRequest req, 
			HttpServletResponse res, 
			@PathVariable int userId) {
		User user = userSvc.show(userId);
		if (user == null) {
			res.setStatus(404);
		}
		return user;
	}
	
	@GetMapping("auth/profile")
	public User show2(HttpServletRequest req, 
			HttpServletResponse res, 
			Principal principal) {
		User user = userSvc.show(principal.getName());
		if (user == null) {
			res.setStatus(404);
		}
		return user;
	}
	
	@PutMapping("auth/users/{userId}")
	public User update(HttpServletRequest req, 
					   HttpServletResponse res,
					   @PathVariable int userId, 
					   @RequestBody User user,
					   Principal principal) {
		try {
			user = userSvc.update(principal.getName(), user, userId);
			if (user == null) {
				res.setStatus(404);
			}
		} catch (Exception e) {
			res.setStatus(400);
			user = null;
		}
		return user;
	}

	
	//Does not work for general user because once disabled, there is no access to login to re-enable.
	//Maybe we can use the code and link it to an enable button when they try to log in (unsure of how
	//hard that would be) Or might be able to use this as beginning code for admin to reenable users.
	
	
	@PutMapping("auth/users/{userId}/enable")
	public void enable(HttpServletRequest req, 
						HttpServletResponse res, 
						@PathVariable int userId,
						Principal principal) {
		try {
			boolean enabled = userSvc.enable(principal.getName(), userId);
			if (enabled) {
				res.setStatus(200);
			}
			else {
				res.setStatus(404);			
			}
		} catch (Exception e) {
			res.setStatus(400);			
		}
	}
	@DeleteMapping("auth/users/{userId}")
	public void disable(HttpServletRequest req, 
			HttpServletResponse res, 
			@PathVariable int userId,
			Principal principal) {
		try {
			boolean disabled = userSvc.disable(principal.getName(), userId);
			if (disabled) {
				res.setStatus(204);
			}
			else {
				res.setStatus(404);			
			}
		} catch (Exception e) {
			res.setStatus(400);			
		}
	}
	
}
