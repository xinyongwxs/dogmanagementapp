package com.ws.dog.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ws.dog.management.entity.TrainingDog;
import com.ws.dog.management.model.DogFilterModel;
import com.ws.dog.management.services.TrainingDogService;

@RestController
@RequestMapping(value = "/trainingdog")
public class TrainingDogController {
	@Autowired
	public TrainingDogService trainingDogService;
	
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody List<TrainingDog> getAll() {
		List<TrainingDog> result = trainingDogService.getAll();
		return result;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody String AddDogs(@RequestBody List<TrainingDog> dogs) {
		if (dogs.size() > 0) {
			trainingDogService.AddDogs(dogs);
		}
		return null;
	}
	
	@RequestMapping(value = "/filtered", method = RequestMethod.POST)
	public @ResponseBody List<TrainingDog> getAllByFilter(@RequestBody DogFilterModel filterObj) {
		List<TrainingDog> result = trainingDogService.getAllByFilter(filterObj.getLimit(), 
				filterObj.getOffset(), filterObj.getFilter(), filterObj.getOrderByName(), filterObj.getSortMethod());
		return result;
	}
}
