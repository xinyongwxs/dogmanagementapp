package com.ws.dog.management.repository.impl;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.ws.dog.management.entity.WorkingDog;
import com.ws.dog.management.repository.CustomRepository;
import com.ws.dog.management.repository.common.DogCommonDao;

public class WorkingDogRepositoryImpl implements CustomRepository<WorkingDog> {
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<WorkingDog> getLimitItems(Integer limit, Integer offset, Map<String, Object> filter, String orderByName,
			String sortMethod) {
		DogCommonDao<WorkingDog> dd = new DogCommonDao<WorkingDog>(){};
		List<WorkingDog> result = dd.getLimitItems(limit, offset, filter, orderByName, sortMethod, em);
		return result;
	}

}
