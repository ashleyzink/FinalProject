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
//	@GetMapping
//	public List<GeneralComment> index(
//			HttpServletResponse res,

	//GET "api/generalcomments"
	@GetMapping("generalComments")
	public List<GeneralComment> lists(){
		return genCommentService.getAllGeneralComments();
	}
	
	//GET "api/generalcomments/{genComId}"
	@GetMapping("generalComments/{comId}")
	public GeneralComment show(
			HttpServletRequest req,
			HttpServletResponse res, 
			@PathVariable int comId 
			) {
		GeneralComment generalComments = genCommentService.findByGeneralCommentId(comId);
		if(generalComments == null) {
			res.setStatus(404);
		}
		return generalComments;
	}
	
	
	
	//POST "api/generalcomments"
	@PostMapping("auth/generalComments")
	public GeneralComment create(
			HttpServletRequest req,
			HttpServletResponse res,
			@RequestBody GeneralComment genComment,
			Principal principal
			) {
		
		try {
			genComment = genCommentService.create(principal.getName(), genComment);
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
	@PutMapping("auth/generalcomments/{genComId}")
	public GeneralComment update(
			HttpServletRequest req,
			HttpServletResponse res,
			@PathVariable int genComId,
			@RequestBody GeneralComment genComment,
			Principal principal 
			) {
		try {
			genComment = genCommentService.update(principal.getName(), genComId, genComment);
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
	@DeleteMapping("auth/generalComments/{genComId}")
	public void destroy(
			HttpServletRequest req,
			HttpServletResponse res,
			@PathVariable int genComId,
			Principal principal
			) {
		try {
			boolean deleted = genCommentService.destroy(principal.getName(), genComId);
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
