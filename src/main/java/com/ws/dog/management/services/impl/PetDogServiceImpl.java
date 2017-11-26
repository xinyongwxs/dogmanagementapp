package com.ws.dog.management.services.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ws.dog.management.entity.PetDog;
import com.ws.dog.management.repository.PetDogRepository;
import com.ws.dog.management.services.PetDogService;

@Service
public class PetDogServiceImpl implements PetDogService {
	@Autowired
	private PetDogRepository petDogRepository;
	@Override
	@Transactional
	public List<PetDog> getAll() {
		return petDogRepository.findAll();
	}

	@Override
	@Transactional
	public void AddDogs(List<PetDog> dogs) {
		petDogRepository.save(dogs);
		
	}

	@Override
	@Transactional
	public void UpdateDogs(Map<String, Object> dogProps) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional
	public List<PetDog> getAllByFilter(Integer limit, Integer offset, Map<String, Object> filter, String orderByName,
			String sortMethod) {
		List<PetDog> result = petDogRepository.getLimitItems(limit, offset, filter, orderByName, sortMethod);
		return result;
	}

}
