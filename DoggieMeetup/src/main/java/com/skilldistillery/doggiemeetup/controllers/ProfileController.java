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

	//To see your own dogs //Possibly to access dog update information?
	@GetMapping("profile/dogs/{dogId}")
	public Dog showUserDog(HttpServletRequest req, 
			HttpServletResponse res, 
			Principal principal, @PathVariable int dogId) {
		Dog dog = dogSvc.showUserDog(principal.getName(), dogId);
		if (dog == null) {
			res.setStatus(404);
		}
		return dog;
	}
	
	//Owner able to see all their dogs on THEIR profile before selecting one
	@GetMapping("profile/dogs")
	public List<Dog> showAllUserDogs(HttpServletRequest req, 
			HttpServletResponse res, 
			Principal principal) {
		List<Dog> dog = dogSvc.index(principal.getName());
		if (dog == null) {
			res.setStatus(404);
		}
		return dog;
	}
	
	//See ANY dog by ID (Option to scrub info later to block view from others)
	@GetMapping("dogs/{dogId}")
	public Dog showDogById(HttpServletRequest req, 
			HttpServletResponse res, 
			@PathVariable int dogId) {
		Dog dog = dogSvc.showDogById(dogId);
		if (dog == null) {
			res.setStatus(404);
		}
		return dog;
	}
	
	@PostMapping("auth/profile/dogs")
	public Dog create(HttpServletRequest req, HttpServletResponse res, @RequestBody Dog dog, Principal principal) {
		try {
			dog = dogSvc.create(principal.getName(), dog);
			res.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/").append(dog.getId());
			String urlstr = url.toString();
			res.setHeader("Location", urlstr);
		} catch (Exception e) {
			res.setStatus(400);
			dog = null;
		}
		return dog;
	}
}
