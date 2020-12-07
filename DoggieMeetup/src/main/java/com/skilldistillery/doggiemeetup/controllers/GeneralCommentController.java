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
import com.skilldistillery.doggiemeetup.entities.GeneralComment;
import com.skilldistillery.doggiemeetup.services.GeneralCommentService;

@CrossOrigin({"*", "http://localhost:4210" })
@RequestMapping("api")
@RestController
public class GeneralCommentController {
	@Autowired
	private GeneralCommentService genCommentService;
	
	//GET "api/generalcomments"
	@GetMapping("generalcomments")
	public List<GeneralComment> index(
			HttpServletResponse res,
			@PathVariable String username,
			Principal principal 
			){
		List<GeneralComment> genCommentsForUser = genCommentService.index(principal.getName());
		if(genCommentsForUser == null) {
			res.setStatus(404);
		}
		return genCommentsForUser;
	}
	//GET "api/generalcomments"
	@GetMapping("generalcomments")
	public List<GeneralComment> lists(){
		return genCommentService.getAllGeneralComments();
	}
	
	//GET "api/generalcomments/{genComId}"
	@GetMapping("generalcomments/{genComId}")
	public GeneralComment show(
			HttpServletRequest req,
			HttpServletResponse res, 
			@PathVariable int genComId,
			@PathVariable String username,
			Principal principal) {
		GeneralComment generalComments = genCommentService.show(username, genComId);
		if(generalComments == null) {
			res.setStatus(404);
		}
		return generalComments;
	}
	
	//POST "api/generalcomments"
	@PostMapping("generalcomments")
	public GeneralComment create(
			HttpServletRequest req,
			HttpServletResponse res,
			@PathVariable String username,
			@RequestBody GeneralComment genComment,
			Principal principal
			) {
		
		try {
			genComment = genCommentService.create(username, genComment);
			res.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/").append(genComment.getId());
			res.setHeader("Location", url.toString());
		} catch (Exception e) {
			res.setStatus(400);
			genComment = null;
		}
		return genComment;
	}
	
	//PUT "api/generalcomments/{genComId}"
	@PutMapping("generalcomments/{genComId}")
	public GeneralComment update(
			HttpServletRequest req,
			HttpServletResponse res,
			@PathVariable String username,
			@PathVariable int genComId,
			@RequestBody GeneralComment genComment,
			Principal principal 
			) {
		try {
			genComment = genCommentService.update(username, genComId, genComment);
			if(genComment == null) {
				res.setStatus(404);
			}
			
		} catch (Exception e) {
			res.setStatus(400);
			genComment = null;
		}
		return genComment;
	}
	
	//DELETE "api/generalcomments/{genComId}
	@DeleteMapping("generalcomments/{genComId}")
	public void destroy(
			HttpServletRequest req,
			HttpServletResponse res,
			@PathVariable String username,
			@PathVariable int genComId,
			Principal principal
			) {
		try {
			boolean deleted = genCommentService.destroy(username, genComId);
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
