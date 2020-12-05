package com.skilldistillery.doggiemeetup.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.doggiemeetup.entities.Route;

public interface RouteRepository extends JpaRepository<Route, Integer> {

}
