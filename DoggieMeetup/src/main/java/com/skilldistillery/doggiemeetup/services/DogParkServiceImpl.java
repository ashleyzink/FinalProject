package com.skilldistillery.doggiemeetup.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.doggiemeetup.entities.DogPark;
import com.skilldistillery.doggiemeetup.repositories.DogParkRepository;

@Service
public class DogParkServiceImpl implements DogParkService {
	
	@Autowired
	private DogParkRepository dogParkRepo;

	@Override
	public List<DogPark> index() {
		return dogParkRepo.findAll();
	}

	@Override
	public DogPark show(int id) {
		Optional<DogPark> dogParkOpt = dogParkRepo.findById(id);
		if (!dogParkOpt.isPresent()) {
			return null;
		}
		return dogParkOpt.get();
	}

	@Override
	public DogPark create(DogPark dogPark) {
		return dogParkRepo.saveAndFlush(dogPark);
	}

	@Override
	public DogPark update(DogPark dogPark, int id) {
		Optional<DogPark> dogParkOpt = dogParkRepo.findById(id);
		DogPark dbDogPark = null;
		if (dogParkOpt.isPresent() && dogParkOpt.get().getId() == id) {
			dbDogPark = dogParkOpt.get();
			if (dbDogPark == null) {
				return null;
			}
		}
		if (dogPark.getOffLeash() != null) { dbDogPark.setOffLeash(dogPark.getOffLeash()); }
		if (dogPark.getAddress() != null) { dbDogPark.setAddress(dogPark.getAddress()); }
		if (dogPark.getName() != null) { dbDogPark.setName(dogPark.getName()); }
		if (dogPark.getDescription() != null) { dbDogPark.setDescription(dogPark.getDescription()); }
		if (dogPark.getImageUrl() != null) { dbDogPark.setImageUrl(dogPark.getImageUrl()); }	
		
		return dogParkRepo.saveAndFlush(dbDogPark);

	}

	@Override
	public Boolean delete(int id) {
		dogParkRepo.deleteById(id);
		return ! dogParkRepo.existsById(id);
	}

}
