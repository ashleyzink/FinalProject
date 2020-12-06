package com.skilldistillery.doggiemeetup.controllers;

import java.security.Principal;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.doggiemeetup.entities.User;
import com.skilldistillery.doggiemeetup.services.UserService;

@CrossOrigin({ "*", "http://localhost:4210" })
@RestController
public class AuthController {
	
	@Autowired
	private UserService userService;

	@PostMapping(path = "/register")
	public User register(@RequestBody User user, HttpServletResponse res) {
	    if (user == null) {
	        res.setStatus(400);
	    }
	    user = userService.register(user);
	    return user;
	}

	@GetMapping(path = "/authenticate")
	public User authenticate(Principal principal) {
	    return userService.show(principal.getName());
	}
	
	
	
}
