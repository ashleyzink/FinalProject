package com.skilldistillery.doggiemeetup.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.doggiemeetup.entities.GeneralComment;

public interface GeneralCommentRepository extends JpaRepository<GeneralComment, Integer> {

}
