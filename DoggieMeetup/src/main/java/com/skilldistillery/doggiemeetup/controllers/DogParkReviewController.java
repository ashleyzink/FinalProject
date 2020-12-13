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

import com.skilldistillery.doggiemeetup.entities.DogParkReview;
import com.skilldistillery.doggiemeetup.services.DogParkReviewService;
import com.skilldistillery.doggiemeetup.services.UserService;

@CrossOrigin({ "*", "http:localhost:4210" })
@RequestMapping("api")
@RestController
public class DogParkReviewController {

	@Autowired
	private DogParkReviewService dogParkReviewSvc;

	@Autowired
	private UserService userSvc;

	@GetMapping("dogParkReviews")
	public List<DogParkReview> index(HttpServletRequest req, HttpServletResponse res) {
		List<DogParkReview> dogParkReviews = dogParkReviewSvc.index();
		if (dogParkReviews == null) {
			res.setStatus(404);
		}
		return dogParkReviews;
	}
	
	@GetMapping("dogParks/{dogParkId}/dogParkReviews")
	public List<DogParkReview> indexByDogPark(HttpServletRequest req, HttpServletResponse res, @PathVariable int dogParkId) {
		List<DogParkReview> dogParkReviews = dogParkReviewSvc.index(dogParkId);
		if (dogParkReviews == null) {
			res.setStatus(404);
		}
		return dogParkReviews;
	}

	@GetMapping("dogParks/{dogParkId}/dogParkReviews/{userId}")
	public DogParkReview show(HttpServletRequest req, HttpServletResponse res, @PathVariable int dogParkId,
			@PathVariable int userId) {
		DogParkReview dogParkReview = dogParkReviewSvc.show(dogParkId, userId);
		if (dogParkReview == null) {
			res.setStatus(404);
		}
		return dogParkReview;
	}

	@PostMapping("auth/dogParks/{dogParkId}/dogParkReviews")
	public DogParkReview create(HttpServletRequest req, HttpServletResponse res, @PathVariable int dogParkId,
			@RequestBody DogParkReview dogParkReview, Principal principal) {
		try {
			System.out.println(dogParkReview);
			dogParkReview = dogParkReviewSvc.create(principal.getName(), dogParkReview, dogParkId);
			if (dogParkReview == null) {
				res.setStatus(404);
			} else {
				res.setStatus(201);
				StringBuffer url = req.getRequestURL();
				url.append("/").append(dogParkReview.getId());
			}
		} catch (Exception e) {
			res.setStatus(400);
			dogParkReview = null;
			e.printStackTrace();
		}
		return dogParkReview;
	}

	@PutMapping("auth/dogParks/{dogParkId}/dogParkReviews/{userId}")
	public DogParkReview update(HttpServletResponse res, HttpServletRequest req, @PathVariable int userId,
			@PathVariable int dogParkId, @RequestBody DogParkReview dogParkReview, Principal principal) {
		try {
			dogParkReview = dogParkReviewSvc.update(principal.getName(), dogParkId, dogParkReview);
			if (dogParkReview == null) {
				res.setStatus(404);
			}
		} catch (Exception e) {
			System.err.println(e);
			res.setStatus(400);
			dogParkReview = null;
		}
		return dogParkReview;
	}

	@DeleteMapping("auth/dogParks/{dogParkId}/dogParkReviews/{userId}")
	public void delete(HttpServletRequest req, HttpServletResponse res, @PathVariable int userId,
			@PathVariable int dogParkId, Principal principal) {
		try {
			boolean deleted = dogParkReviewSvc.destroy(principal.getName(), userId, dogParkId);
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
