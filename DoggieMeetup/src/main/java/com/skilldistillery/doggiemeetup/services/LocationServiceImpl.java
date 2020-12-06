package com.skilldistillery.doggiemeetup.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.doggiemeetup.entities.Location;
import com.skilldistillery.doggiemeetup.repositories.LocationRepository;

@Service
public class LocationServiceImpl implements LocationService {
	
	@Autowired
	private LocationRepository locRepo;

	@Override
	public List<Location> getAllLocations() {
		return locRepo.findAll();
	}

	@Override
	public Location show(int id) {
		Optional<Location> locOpt = locRepo.findById(id);
		Location loc = null;
		if (locOpt.isPresent()) {
			loc = locOpt.get();
		}
		return loc;
	}

	@Override
	public Location create(Location location) {
		return locRepo.saveAndFlush(location);
	}

	@Override
	public Location update(int id, Location location) {
		Optional<Location> locOpt = locRepo.findById(id);
		Location managedLocation = null;
		if(locOpt.isPresent()) {
			managedLocation = locOpt.get();
			if (location.getLat() != null) {managedLocation.setLat(location.getLat());}
			if (location.getLng() != null) {managedLocation.setLng(location.getLng());}
			if (location.getPointTime() != null) {managedLocation.setPointTime(location.getPointTime());}
		}
		return managedLocation;
	}

	@Override
	public boolean destroy(int id, Location location) {
		boolean deleted = false;
		Optional<Location> locOpt = locRepo.findById(id);
		if (locOpt.isPresent()) {
			location = locOpt.get();
			locRepo.delete(location);
			deleted = true;
		}
		return deleted;
	}

}
