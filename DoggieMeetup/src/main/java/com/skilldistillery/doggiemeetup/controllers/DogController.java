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
import com.skilldistillery.doggiemeetup.services.DogService;


@CrossOrigin({"*", "http://localhost:4201"})
@RequestMapping("api")
@RestController
public class DogController {
	
	@Autowired
	private DogService dogSvc;
	
	//TODO: Search for dogs by keywords (name/description/temperment/activity level)
	
	@GetMapping("dogs")
	private List<Dog> getAllDogs() {
		return dogSvc.getAllDogs();
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
	
	//To see your own dogs //Possibly to access dog update information?
	@GetMapping("auth/dogs/{dogId}")
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
	@GetMapping("auth/dogs")
	public List<Dog> showAllUserDogs(HttpServletRequest req, 
			HttpServletResponse res, 
			Principal principal) {
		List<Dog> dog = dogSvc.index(principal.getName());
		if (dog == null) {
			res.setStatus(404);
		}
		return dog;
	}
	
	
	@PostMapping("auth/dogs")
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
	
	@PutMapping("auth/dogs/{dogId}")
	public Dog update(HttpServletRequest req, 
					   HttpServletResponse res,
					   @PathVariable int dogId, 
					   @RequestBody Dog dog,
					   Principal principal) {
		try {
			dog = dogSvc.update(principal.getName(), dogId, dog);
			if (dog == null) {
				res.setStatus(404);
			}
		} catch (Exception e) {
			res.setStatus(400);
			dog = null;
		}
		return dog;
	}
	
	
	//TODO: Make this function cascading so will still delete if there are comments or ratings about the dog.
	
	@DeleteMapping("auth/dogs/{dogId}")
	public void destroy(HttpServletRequest req, 
						HttpServletResponse res, 
						@PathVariable int dogId,
						Principal principal) {
		try {
			boolean deleted = dogSvc.destroy(principal.getName(), dogId);
			if (deleted) {
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
