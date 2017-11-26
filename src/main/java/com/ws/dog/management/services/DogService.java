package com.ws.dog.management.services;

import java.util.List;
import java.util.Map;

public interface DogService<T> {
	public List<T> getAll();
	
	public void AddDogs(List<T> dogs);

	public void UpdateDogs(Map<String, Object> dogProps);
	
	public List<T> getAllByFilter(Integer limit, Integer offset, Map<String, Object> filter,
			String orderByName, String sortMethod);
}
