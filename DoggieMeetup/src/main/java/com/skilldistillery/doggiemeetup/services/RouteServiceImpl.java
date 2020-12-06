package com.skilldistillery.doggiemeetup.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.doggiemeetup.entities.Route;
import com.skilldistillery.doggiemeetup.entities.User;
import com.skilldistillery.doggiemeetup.repositories.RouteRepository;
import com.skilldistillery.doggiemeetup.repositories.UserRepository;

@Service
public class RouteServiceImpl implements RouteService {
	
	@Autowired
	private RouteRepository routeRepo;
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public List<Route> index(String username) {
		if (userRepo.findByUsername(username) == null) {
			return null;
		}
		return routeRepo.findByUser_Username(username);
	}

	@Override
	public List<Route> getAllRoutes() {
		return routeRepo.findAll();
	}

	@Override
	public Route show(String username, int routeId) {
		return routeRepo.findByUser_UsernameAndId(username, routeId);
	}

	@Override
	public Route create(String username, Route route) {
		User user = userRepo.findByUsername(username);
		if (user != null) {
			route.setUser(user);
			routeRepo.saveAndFlush(route);
		}
		return route;
	}

	@Override
	public Route update(String username, int routeId, Route route) {
		Route managedRoute = routeRepo.findByUser_UsernameAndId(username, routeId);
		if (managedRoute != null) {
			managedRoute.setStartTime(route.getStartTime());
			managedRoute.setEndTime(route.getEndTime());
			routeRepo.saveAndFlush(managedRoute);
		}
		return managedRoute;
	}

	@Override
	public boolean destroy(String username, int routeId) {
		boolean deleted = false;
		Route route = routeRepo.findByUser_UsernameAndId(username, routeId);
		if (route != null) {
			routeRepo.delete(route);
			deleted = true;
		}
		return deleted;
	}
	
	

}
