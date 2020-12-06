package com.skilldistillery.doggiemeetup.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.doggiemeetup.entities.Route;

public interface RouteRepository extends JpaRepository<Route, Integer> {

	List<Route> findByUser_Username(String username);
	
	Route findByUser_UsernameAndId(String username, int routeId);
}
