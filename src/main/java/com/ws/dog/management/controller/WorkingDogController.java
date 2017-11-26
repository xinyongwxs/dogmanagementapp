package com.ws.dog.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ws.dog.management.entity.WorkingDog;
import com.ws.dog.management.model.DogFilterModel;
import com.ws.dog.management.services.WorkingDogService;

@RestController
@RequestMapping(value = "/workingdog")
public class WorkingDogController {
	@Autowired
	public WorkingDogService workingDogService;
	
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody List<WorkingDog> getAll() {
		List<WorkingDog> result = workingDogService.getAll();
		return result;
	}
	
	
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody String AddDogs(@RequestBody List<WorkingDog> dogs) {
		if (dogs.size() > 0) {
			workingDogService.AddDogs(dogs);
		}
		return null;
	}
	
	@RequestMapping(value = "/filtered", method = RequestMethod.POST)
	public @ResponseBody List<WorkingDog> getAllByFilter(@RequestBody DogFilterModel filterObj) {
		List<WorkingDog> result = workingDogService.getAllByFilter(filterObj.getLimit(), 
				filterObj.getOffset(), filterObj.getFilter(), filterObj.getOrderByName(), filterObj.getSortMethod());
		return result;
	}
}
