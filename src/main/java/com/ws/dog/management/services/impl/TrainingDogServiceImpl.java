package com.ws.dog.management.services.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ws.dog.management.entity.TrainingDog;
import com.ws.dog.management.repository.TrainingDogRepository;
import com.ws.dog.management.services.TrainingDogService;

@Service
public class TrainingDogServiceImpl implements TrainingDogService {

	@Autowired
	private TrainingDogRepository trainingDogRepository;
	@Override
	public List<TrainingDog> getAll() {
		return trainingDogRepository.findAll();
	}

	@Override
	public void AddDogs(List<TrainingDog> dogs) {
		trainingDogRepository.save(dogs);
	}

	@Override
	public void UpdateDogs(Map<String, Object> dogProps) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<TrainingDog> getAllByFilter(Integer limit, Integer offset, Map<String, Object> filter,
			String orderByName, String sortMethod) {
		List<TrainingDog> result = trainingDogRepository.getLimitItems(limit, offset, filter, orderByName, sortMethod);
		return result;
	}

}
