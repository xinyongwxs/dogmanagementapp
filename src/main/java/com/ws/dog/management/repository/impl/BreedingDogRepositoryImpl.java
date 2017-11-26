package com.ws.dog.management.repository.impl;

import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.ws.dog.management.entity.BreedingDog;
import com.ws.dog.management.repository.CustomRepository;
import com.ws.dog.management.repository.common.DogCommonDao;

public class BreedingDogRepositoryImpl implements CustomRepository<BreedingDog> {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<BreedingDog> getLimitItems(Integer limit, Integer offset, Map<String, Object> filter,
			String orderByName, String sortMethod) {
		DogCommonDao<BreedingDog> dd = new DogCommonDao<BreedingDog>(){};
		List<BreedingDog> result = dd.getLimitItems(limit, offset, filter, orderByName, sortMethod, em);
		return result;
	}
}
