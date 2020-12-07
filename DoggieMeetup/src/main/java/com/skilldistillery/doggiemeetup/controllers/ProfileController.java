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

import com.skilldistillery.doggiemeetup.entities.Dog;
import com.skilldistillery.doggiemeetup.entities.User;
import com.skilldistillery.doggiemeetup.services.DogService;
import com.skilldistillery.doggiemeetup.services.UserService;

@CrossOrigin({"*", "http://localhost:4201"})
@RequestMapping("api")
@RestController
public class ProfileController {
	
	@Autowired
	private UserService userSvc;
	
	@Autowired
	private DogService dogSvc;
	
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

//	@GetMapping("dogs/{dogId}")
//	public Dog show(HttpServletRequest req, 
//			HttpServletResponse res, 
//			Principal principal, @PathVariable int dogId) {
//		Dog dog = dogSvc.show(principal.getName(), dogId);
//		if (dog == null) {
//			res.setStatus(404);
//		}
//		return dog;
//	}
//	@GetMapping("/profile/dogs/")
//	public Dog show(HttpServletRequest req, 
//			HttpServletResponse res, 
//			Principal principal, @PathVariable int dogId) {
//		Dog dog = dogSvc.show(principal.getName(), dogId);
//		if (dog == null) {
//			res.setStatus(404);
//		}
//		return dog;
//	}
	
	@PostMapping("users/{userId}/dogs")
	public Dog create(HttpServletRequest req, HttpServletResponse res, @RequestBody Dog dog, @PathVariable int userId) {
		try {
			dog = dogSvc.create(userId, dog);
			res.setStatus(201);
			StringBuffer url = req.getRequestURL();
//			url.append("/").append(cookie.getId()); //The append("/") is adding an extra / to the URL 
			url.append(dog.getId());
			String urlstr = url.toString();
			res.setHeader("Location", urlstr);
		} catch (Exception e) {
			res.setStatus(400);
			dog = null;
		}
		return dog;
	}
}
