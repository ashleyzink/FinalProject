package com.skilldistillery.doggiemeetup.services;

import java.util.List;

import com.skilldistillery.doggiemeetup.entities.Dog;

public interface DogService {
	
	//For User profile
	public List<Dog> index(String username);

	//To search for all dogs regardless of user
	public List<Dog> getAllDogs();
	
	public Dog showUserDog(String username, int dogId);
	
	public Dog showDogById(int dogId);
	
	public Dog create(String username, Dog dog);
	
	public Dog update(String username, int dogId, Dog dog);
	
	public boolean destroy(String username, int dogId);
}
