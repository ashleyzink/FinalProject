package com.skilldistillery.doggiemeetup.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.doggiemeetup.entities.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {

}
