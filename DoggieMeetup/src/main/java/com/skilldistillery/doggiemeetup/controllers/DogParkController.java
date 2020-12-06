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

import com.skilldistillery.doggiemeetup.entities.DogPark;
import com.skilldistillery.doggiemeetup.services.DogParkService;

@CrossOrigin({ "*", "http:localhost:4210" })
@RequestMapping("api")
@RestController
public class DogParkController {

	@Autowired
	private DogParkService dogparkSvc;

	@GetMapping("dogparks")
	public List<DogPark> index(HttpServletRequest req, HttpServletResponse res, Principal principal) {
		List<DogPark> dogparks = dogparkSvc.index();
		principal.getName();
		if (dogparks == null) {
			res.setStatus(404);
		}

		return dogparks;
	}

	@GetMapping("dogparks/{id}")
	public DogPark show(HttpServletRequest req, HttpServletResponse res, @PathVariable int id, Principal principal) {
		DogPark dogpark = dogparkSvc.show(id);
		if (dogpark == null) {
			res.setStatus(404);
		}
		return dogpark;
	}

	@PostMapping("dogparks")
	public DogPark create(HttpServletRequest req, HttpServletResponse res, @RequestBody DogPark dogpark,
			Principal principal) {
		try {
			dogpark = dogparkSvc.create(dogpark);
			if (dogpark == null) {
				res.setStatus(404);
			} else {
				res.setStatus(201);
				StringBuffer url = req.getRequestURL();
				url.append("/").append(dogpark.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			dogpark = null;
		}
		return dogpark;
	}

	@PutMapping("dogparks/{id}")
	public DogPark update(HttpServletRequest req, HttpServletResponse res, @PathVariable int id,
			@RequestBody DogPark dogpark, Principal principal) {
		try {
			dogpark = dogparkSvc.update(dogpark, id);
			if (dogpark == null) {
				res.setStatus(404);
			}
		} catch (Exception e) {
			res.setStatus(400);
			dogpark = null;
		}
		return dogpark;
	}

	@DeleteMapping("dogparks/{id}")
	public void destroy(HttpServletResponse res, HttpServletRequest req, @PathVariable int id, Principal principal) {
		try {
			boolean deleted = dogparkSvc.delete(id);
			if (deleted) {
				res.setStatus(204);
			} else {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
	}

}
