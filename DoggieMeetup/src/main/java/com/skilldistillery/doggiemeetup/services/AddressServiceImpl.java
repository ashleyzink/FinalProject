package com.skilldistillery.doggiemeetup.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.doggiemeetup.entities.Address;
import com.skilldistillery.doggiemeetup.repositories.AddressRepository;

@Service
public class AddressServiceImpl implements AddressService {
	
	@Autowired
	private AddressRepository addrRepo;

	@Override
	public List<Address> getAllAddresses() {
		return addrRepo.findAll();
	}

	@Override
	public Address show(int id) {
		Optional<Address> addrOpt = addrRepo.findById(id);
		Address addr = null;
		if (addrOpt.isPresent()) {
			addr = addrOpt.get();
		}
		return addr;
	}

	@Override
	public Address create(Address address) {
		
		return addrRepo.saveAndFlush(address);
	}

	@Override
	public Address update(int id, Address address) {
		Optional<Address> addrOpt = addrRepo.findById(id);
		Address managedAddress = null;
		if(addrOpt.isPresent()) {
			managedAddress = addrOpt.get();
			if (address.getStreet() != null) {managedAddress.setStreet(address.getStreet());}
			if (address.getCity() != null) {managedAddress.setCity(address.getCity());}
			if (address.getStateAbbrv() != null) {managedAddress.setStateAbbrv(address.getStateAbbrv());}
			if (address.getZipcode() != null) {managedAddress.setZipcode(address.getZipcode());}
			addrRepo.saveAndFlush(managedAddress);
 		}
		
		return managedAddress;
	}

	@Override
	public boolean destroy(int id, Address address) {
		boolean deleted = false;
		Optional<Address> addrOpt = addrRepo.findById(id);
		if (addrOpt.isPresent()) {
			address = addrOpt.get();
			addrRepo.delete(address);
			deleted = true;
		}
		return deleted;
	}

}
