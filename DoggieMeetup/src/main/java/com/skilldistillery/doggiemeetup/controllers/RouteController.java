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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.doggiemeetup.entities.Route;
import com.skilldistillery.doggiemeetup.services.RouteService;
import com.skilldistillery.doggiemeetup.services.UserService;

@CrossOrigin({"*", "http://localhost:4201"})
@RequestMapping("api")
@RestController
public class RouteController {
	

	@Autowired
	private RouteService routeSvc;
	
	
	@GetMapping("routes")
	private List<Route> index() {
		return routeSvc.getAllRoutes();
	}
	
	@GetMapping("auth/profile/routes")
	public List<Route> indexUserRoutes(HttpServletRequest req, 
			HttpServletResponse res, 
			Principal principal) {
		List<Route> routes = routeSvc.index(principal.getName());	
		return routes;
	}
	

	@PostMapping("auth/profile/routes")
	public void enable(HttpServletRequest req, 
						HttpServletResponse res, 
						@RequestBody Route route,
						Principal principal) {
		try {
			routeSvc.create(principal.getName(), route);
			if (route == null) {
				res.setStatus(404);			
			}
		} catch (Exception e) {
			res.setStatus(400);			
		}
	}
	
}
