package com.skilldistillery.doggiemeetup.services;

import java.util.List;

import com.skilldistillery.doggiemeetup.entities.Address;

public interface AddressService {
	
	public List<Address> getAllAddresses();
	
	public Address show(int id);
	
	public Address create(Address address);
	
	public Address update(int id, Address address);
	
	public boolean destroy(int id, Address address);

}
