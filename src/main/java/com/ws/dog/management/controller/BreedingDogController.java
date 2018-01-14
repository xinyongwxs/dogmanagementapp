package com.ws.dog.management.controller;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ws.dog.management.entity.BreedingDog;
import com.ws.dog.management.model.DogFilterModel;
import com.ws.dog.management.services.BreedingDogService;


@RestController
@RequestMapping(value = "/breedingdog")
public class BreedingDogController {
	@Autowired
	public BreedingDogService breedingDogService;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<BreedingDog> getAll() {
		List<BreedingDog> wdList = breedingDogService.getAll();
		return wdList;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String AddDogs(@RequestBody List<BreedingDog> dogs) {
		if (dogs.size() > 0) {
			breedingDogService.AddDogs(dogs);
		}
		return null;
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public void updateDog(@RequestBody Map<String, Object> dogProps) {
		breedingDogService.UpdateDogs(dogProps);
	}
	
	@RequestMapping(value = "/filtered", method = RequestMethod.POST)
	public List<BreedingDog> getAllByFilter(@RequestBody DogFilterModel filterObj) {
		List<BreedingDog> result = breedingDogService.getAllByFilter(filterObj.getLimit(), 
				filterObj.getOffset(), filterObj.getFilter(), filterObj.getOrderByName(), filterObj.getSortMethod());
		return result;
	}
}
