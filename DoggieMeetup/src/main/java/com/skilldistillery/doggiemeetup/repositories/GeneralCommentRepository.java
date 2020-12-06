package com.skilldistillery.doggiemeetup.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.doggiemeetup.entities.GeneralComment;

public interface GeneralCommentRepository extends JpaRepository<GeneralComment, Integer> {
	
	List<GeneralComment> findbyUser_Username(String username);
	
	GeneralComment findByUser_UsernameAndId(String username, int genComId);

}
