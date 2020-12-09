package com.skilldistillery.doggiemeetup.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.doggiemeetup.entities.DogParkReview;
import com.skilldistillery.doggiemeetup.services.DogParkReviewService;

@CrossOrigin({ "*", "http:localhost:4210" })
@RequestMapping("api")
@RestController
public class DogParkReviewController {

	@Autowired
	private DogParkReviewService dogParkReviewSvc;

	@GetMapping("dogParks/{dogParkId}/dogParkReviews")
	public List<DogParkReview> index(HttpServletRequest req, HttpServletResponse res) {
		List<DogParkReview> dogParkReviews = dogParkReviewSvc.index();
		if (dogParkReviews == null) {
			res.setStatus(404);
		}
		return dogParkReviews;
	}

}
