package com.skilldistillery.doggiemeetup.services;

import java.util.List;

import com.skilldistillery.doggiemeetup.entities.Location;

public interface LocationService {
	
	public List<Location> getAllLocations();
	
	public Location show(int id);
	
	public Location create(Location location);
	
	public Location update(int id, Location location);
	
	public boolean destroy(int id, Location location);

}
