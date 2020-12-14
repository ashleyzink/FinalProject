package com.skilldistillery.doggiemeetup.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.doggiemeetup.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	User findByUsername(String username);
	
	User findByEmail(String email);
	
	List<User> findByUsernameOrEmailOrFirstNameOrLastNameOrBioLike(String keyword1, String keyword2, String keyword3, String keyword4, String keyword5);
	

}
