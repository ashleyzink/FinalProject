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

import com.skilldistillery.doggiemeetup.entities.DogParkComment;
import com.skilldistillery.doggiemeetup.services.DogParkCommentService;
import com.skilldistillery.doggiemeetup.services.UserService;

@CrossOrigin({"*", "http://localhost:4210" })
@RequestMapping("api")
@RestController
public class DogParkCommentController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private DogParkCommentService dogParkCommentService;

	
	//GET "api/dogparkcomments"
	@GetMapping("auth/dogParkComments")
	public List<DogParkComment> index(
			HttpServletResponse res,
			@PathVariable String username,
			Principal principal 
			){
		List<DogParkComment> dogParkCommentsForUser = dogParkCommentService.index(username);
		if(dogParkCommentsForUser == null) {
			res.setStatus(404);
		}
		return dogParkCommentsForUser;
	}
	//GET "api/dogparkcomments"
	@GetMapping("dogParkComments")
	public List<DogParkComment> lists(){
		return dogParkCommentService.getAllDogParkComments();
	}
	//GET "api/dogpark/{dogParkId}/dogParkComments/{comId}"
	@GetMapping("dogParks/{dogParkId}/dogParkComments/{comId}")
	public DogParkComment show(
			HttpServletRequest req,
			HttpServletResponse res, 
			@PathVariable int comId
		) {
		DogParkComment dogParkComments = dogParkCommentService.show(comId);
		if(dogParkComments == null) {
			res.setStatus(404);
		}
		return dogParkComments;
	}
	//POST "api/dogparkcomments"
	@PostMapping("auth/dogParks/{dogParkId}/dogParkComments")
	public DogParkComment create(
			HttpServletRequest req,
			HttpServletResponse res,
			@RequestBody DogParkComment dogParkComment,
			Principal principal
			) {
		
		try {
			dogParkComment = dogParkCommentService.create(principal.getName(), dogParkComment);
			res.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/").append(dogParkComment.getId());
			res.setHeader("Location", url.toString());
		} catch (Exception e) {
			res.setStatus(400);
			dogParkComment = null;
		}
		return dogParkComment;
	}
	
//PUT "api/dogparkcomments/{dogParkCommentId}"
		@PutMapping("auth/dogparks/{dogParkId}/dogParkComments/{comId}")
		public DogParkComment update(
				HttpServletRequest req,
				HttpServletResponse res,
				@PathVariable int comId,
				@RequestBody DogParkComment dogParkComment,
				Principal principal 
				) {
			try {
				dogParkComment = dogParkCommentService.update(principal.getName(), comId, dogParkComment);
				if(dogParkComment == null) {
					res.setStatus(404);
				}
				
			} catch (Exception e) {
				res.setStatus(400);
				dogParkComment = null;
			}
			return dogParkComment;
		}
		
//DELETE "api/dogparkcomments/{dogParkCommentId}"	
		@DeleteMapping("auth/dogparks/{dogParkId}/dogParkComments/{comId}")
		public void destroy(
				HttpServletRequest req,
				HttpServletResponse res,
				@PathVariable int comId,
				Principal principal
				) {
			try {
				boolean deleted = dogParkCommentService.destroy(principal.getName(), comId);
				if(deleted) {
					res.setStatus(204);
				}else {
					res.setStatus(404);
				}
			} catch (Exception e) {
				res.setStatus(400);
			}
		}
	
}
