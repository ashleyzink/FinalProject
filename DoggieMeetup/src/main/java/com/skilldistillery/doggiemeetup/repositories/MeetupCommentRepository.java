package com.skilldistillery.doggiemeetup.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.doggiemeetup.entities.MeetupComment;

public interface MeetupCommentRepository extends JpaRepository<MeetupComment, Integer> {

List<MeetupComment> findbyUser_Username(String username);
	
	MeetupComment findByUser_UsernameAndId(String username, int meetupComId);
}
