//package com.skilldistillery.doggiemeetup.controllers;
//
//import java.security.Principal;
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.skilldistillery.doggiemeetup.entities.DogParkComment;
//import com.skilldistillery.doggiemeetup.services.DogParkCommentService;
//import com.skilldistillery.doggiemeetup.services.UserService;
//
//@CrossOrigin({"*", "http://localhost:4210" })
//@RequestMapping("api")
//@RestController
//public class DogParkCommentController {
//	
//	@Autowired
//	private UserService userService;
//	@Autowired
//	private DogParkCommentService dogParkCommentService;
//
//	
//	//GET "api/dogparkcomments"
//	@GetMapping("dogparkcomments")
//	public List<DogParkComment> index(
//			HttpServletResponse res,
//			@PathVariable String username,
//			Principal principal 
//			){
//		List<DogParkComment> dogParkCommentsForUser = dogParkCommentService.index(principal.getName());
//		if(dogParkCommentsForUser == null) {
//			res.setStatus(404);
//		}
//		return dogParkCommentsForUser;
//	}
//	//GET "api/dogparkcomments"
//	@GetMapping("dogparkcomments")
//	public List<DogParkComment> lists(){
//		return dogParkCommentService.getAllDogParkComments();
//	}
//	//GET "api/dogparkcomments/{dogParkCommentId}"
//	@GetMapping("dogparkcomments/{dogParkCommentId}")
//	public DogParkComment show(
//			HttpServletRequest req,
//			HttpServletResponse res, 
//			@PathVariable int dogParkCommentId,
//			@PathVariable String username,
//			Principal principal) {
//		DogParkComment dogParkComments = dogParkCommentService.show(username, dogParkCommentId);
//		if(dogParkComments == null) {
//			res.setStatus(404);
//		}
//		return dogParkComments;
//	}
//	//POST "api/dogparkcomments"
//	@PostMapping("dogparkcomments")
//	public DogParkComment create(
//			HttpServletRequest req,
//			HttpServletResponse res,
//			@PathVariable String username,
//			@RequestBody DogParkComment dogParkComment,
//			Principal principal
//			) {
//		
//		try {
//			dogParkComment = dogParkCommentService.create(username, dogParkComment);
//			res.setStatus(201);
//			StringBuffer url = req.getRequestURL();
//			url.append("/").append(dogParkComment.getId());
//			res.setHeader("Location", url.toString());
//		} catch (Exception e) {
//			res.setStatus(400);
//			dogParkComment = null;
//		}
//		return dogParkComment;
//	}
//	
////PUT "api/dogparkcomments/{dogParkCommentId}"
//		@PutMapping("dogparkcomments/{dogParkCommentId}")
//		public DogParkComment update(
//				HttpServletRequest req,
//				HttpServletResponse res,
//				@PathVariable String username,
//				@PathVariable int dogParkCommentId,
//				@RequestBody DogParkComment dogParkComment,
//				Principal principal 
//				) {
//			try {
//				dogParkComment = dogParkCommentService.update(username, dogParkCommentId, dogParkComment);
//				if(dogParkComment == null) {
//					res.setStatus(404);
//				}
//				
//			} catch (Exception e) {
//				res.setStatus(400);
//				dogParkComment = null;
//			}
//			return dogParkComment;
//		}
//		
////DELETE "api/dogparkcomments/{dogParkCommentId}"	
//		@DeleteMapping("dogparkcomments/{dogParkCommentId}")
//		public void destroy(
//				HttpServletRequest req,
//				HttpServletResponse res,
//				@PathVariable String username,
//				@PathVariable int dogParkCommentId,
//				Principal principal
//				) {
//			try {
//				boolean deleted = dogParkCommentService.destroy(username, dogParkCommentId);
//				if(deleted) {
//					res.setStatus(204);
//				}else {
//					res.setStatus(404);
//				}
//			} catch (Exception e) {
//				res.setStatus(400);
//			}
//		}
//	
//}
