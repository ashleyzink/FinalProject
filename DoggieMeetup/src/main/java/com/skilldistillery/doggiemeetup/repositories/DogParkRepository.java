package com.skilldistillery.doggiemeetup.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.doggiemeetup.entities.DogPark;

public interface DogParkRepository extends JpaRepository<DogPark, Integer> {

}
