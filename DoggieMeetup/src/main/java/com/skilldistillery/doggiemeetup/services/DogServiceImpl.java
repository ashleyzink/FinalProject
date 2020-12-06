package com.skilldistillery.doggiemeetup.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.doggiemeetup.entities.Dog;
import com.skilldistillery.doggiemeetup.entities.User;
import com.skilldistillery.doggiemeetup.repositories.DogRepository;
import com.skilldistillery.doggiemeetup.repositories.UserRepository;

@Service
public class DogServiceImpl implements DogService {
	
	@Autowired
	private DogRepository dogRepo;
	
	@Autowired
	private UserRepository userRepo;
	

	@Override
	public List<Dog> index(String username) {
		if (userRepo.findByUsername(username) == null) {
			return null;
		}
		return dogRepo.findByUser_Username(username);
	}

	@Override
	public List<Dog> getAllDogs() {
		return dogRepo.findAll();
	}

	@Override
	public Dog show(String username, int dogId) {
		return dogRepo.findByUser_UsernameAndId(username, dogId);
	}

	@Override
	public Dog create(String username, Dog dog) {
		User user = userRepo.findByUsername(username);
		if (user != null) {
			dog.setUser(user);
			dogRepo.saveAndFlush(dog);
		}
		return dog;
	}

	@Override
	public Dog update(String username, int dogId, Dog dog) {
		Dog managedDog = dogRepo.findByUser_UsernameAndId(username, dogId);
		if (managedDog != null) {
			managedDog.setName(dog.getName());
			managedDog.setBreed(dog.getBreed());
			managedDog.setTemperament(dog.getTemperament());
			managedDog.setProfilePicUrl(dog.getProfilePicUrl());
			managedDog.setActivityLevel(dog.getActivityLevel());
			managedDog.setSize(dog.getSize());
			managedDog.setBio(dog.getBio());
			managedDog.setBirthday(dog.getBirthday());
			managedDog.setRainbowBridge(dog.getRainbowBridge());
			managedDog.setCreateDate(dog.getCreateDate());
			dogRepo.saveAndFlush(managedDog);
		}
		return managedDog;
	}

	@Override
	public boolean destroy(String username, int dogId) {
		boolean deleted = false;
		Dog dog = dogRepo.findByUser_UsernameAndId(username, dogId);
		if (dog != null) {
			dogRepo.delete(dog);
			deleted = true;
		}
		return deleted;
	}
	


}
