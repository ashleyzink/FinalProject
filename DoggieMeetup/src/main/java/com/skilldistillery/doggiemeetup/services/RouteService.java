package com.skilldistillery.doggiemeetup.services;

import java.util.List;

import com.skilldistillery.doggiemeetup.entities.Route;

public interface RouteService {
	
	//For User profile
		public List<Route> index(String username);

		//To search for all dogs regardless of user
		public List<Route> getAllRoutes();
		
		public Route show(String username, int routeId);
		
		public Route create(String username, Route route);
		
		public Route update(String username, int routeId, Route route);
		
		public boolean destroy(String username, int routeId);

}
