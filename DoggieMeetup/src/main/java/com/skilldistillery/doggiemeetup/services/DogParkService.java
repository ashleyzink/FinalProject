package com.skilldistillery.doggiemeetup.services;

import java.util.List;

import com.skilldistillery.doggiemeetup.entities.DogPark;

public interface DogParkService {

	List<DogPark> index();
	DogPark show(int id);
	DogPark create(DogPark dogPark, String username);
	DogPark update(DogPark dogPark, int id, String username);
	Boolean delete(int id, String username);
}
