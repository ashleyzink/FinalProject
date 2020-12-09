package com.skilldistillery.doggiemeetup.services;

import java.util.List;

import com.skilldistillery.doggiemeetup.entities.DogParkComment;

public interface DogParkCommentService {
	public List<DogParkComment> index(String username);
	public List<DogParkComment> getCommentsByUserId(int userId);
	public List<DogParkComment> getAllDogParkComments(); 
	public DogParkComment show(int dogParkCommentId);
	public DogParkComment create(String username, DogParkComment dogParkComment);
	public DogParkComment update(String username, int dogParkCommentId, DogParkComment dogParkComment);
	public boolean destroy(String username, int dogParkCommentId);

}
