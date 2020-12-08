package com.skilldistillery.doggiemeetup.controllers;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.doggiemeetup.entities.User;
import com.skilldistillery.doggiemeetup.services.AuthService;

@CrossOrigin({ "*", "http://localhost:4210" })
@RestController
public class AuthController {
	
	@Autowired
	private AuthService authService;

	@PostMapping(path = "/register")
	public User register(@RequestBody User user, HttpServletResponse res) {
		System.out.println("----- in /register");
	    if (user == null) {
	        res.setStatus(400);
	    }
	    try {
			user = authService.register(user);
		} catch (Exception e) {
			res.setStatus(400);
			user = null;

		}
	    return user;
	}

	@GetMapping(path = "/authenticate")
	public User authenticate(Principal principal) {
		System.out.println("----- in /authenticate");
	    return authService.getUser(principal.getName());
	}
	
	
	
}
