package com.skilldistillery.doggiemeetup.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.doggiemeetup.entities.MeetupComment;

public interface MeetupCommentRepository extends JpaRepository<MeetupComment, Integer> {

}
