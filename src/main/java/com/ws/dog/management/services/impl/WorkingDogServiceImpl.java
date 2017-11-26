package com.ws.dog.management.services.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ws.dog.management.entity.WorkingDog;
import com.ws.dog.management.repository.WorkingDogRepository;
import com.ws.dog.management.services.WorkingDogService;

@Service
public class WorkingDogServiceImpl implements WorkingDogService {
	
	@Autowired
	private WorkingDogRepository workingDogRepository;
	
	@Override
	public List<WorkingDog> getAll() {
		return workingDogRepository.findAll();
	}

	@Override
	public void AddDogs(List<WorkingDog> dogs) {
		workingDogRepository.save(dogs);
	}

	@Override
	public void UpdateDogs(Map<String, Object> dogProps) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<WorkingDog> getAllByFilter(Integer limit, Integer offset, Map<String, Object> filter,
			String orderByName, String sortMethod) {
		List<WorkingDog> result = workingDogRepository.getLimitItems(limit, offset, filter, orderByName, sortMethod);
		return result;
	}

}
