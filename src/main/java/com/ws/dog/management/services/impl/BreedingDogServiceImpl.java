package com.ws.dog.management.services.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ws.dog.management.entity.BreedingDog;
import com.ws.dog.management.repository.BreedingDogRepository;
import com.ws.dog.management.services.BreedingDogService;

@Service
public class BreedingDogServiceImpl implements BreedingDogService {

	@Autowired
	private BreedingDogRepository breedingDogRepository;
	
	@Override
	@Transactional
	public List<BreedingDog> getAll() {
		return breedingDogRepository.findAll();
	}

	@Override
	@Transactional
	public void AddDogs(List<BreedingDog> dogs) {
		breedingDogRepository.save(dogs);
	}

	@Override
	@Transactional
	public void UpdateDogs(Map<String, Object> dogProps) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional
	public List<BreedingDog> getAllByFilter(Integer limit, Integer offset, Map<String, Object> filter,
			String orderByName, String sortMethod) {
		List<BreedingDog> result = breedingDogRepository.getLimitItems(limit, offset, filter, orderByName, sortMethod);
		return result;
	}

}
