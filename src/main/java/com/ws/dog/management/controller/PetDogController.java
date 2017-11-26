package com.ws.dog.management.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ws.dog.management.entity.PetDog;
import com.ws.dog.management.model.DogFilterModel;
import com.ws.dog.management.services.PetDogService;

@RestController
@RequestMapping(value = "/petdog")
public class PetDogController {
	@Autowired
	public PetDogService petDogService;
	
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody List<PetDog> getAll() {
		List<PetDog> wdList = petDogService.getAll();
		return wdList;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody String AddDogs(@RequestBody List<PetDog> dogs) {
		if (dogs.size() > 0) {
			petDogService.AddDogs(dogs);
		}
		return null;
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public void updateDog(@RequestBody Map<String, Object> dogProps) {
		petDogService.UpdateDogs(dogProps);
	}
	
	@RequestMapping(value = "/filtered", method = RequestMethod.POST)
	public @ResponseBody List<PetDog> getAllByFilter(@RequestBody DogFilterModel filterObj) {
		List<PetDog> result = petDogService.getAllByFilter(filterObj.getLimit(), 
				filterObj.getOffset(), filterObj.getFilter(), filterObj.getOrderByName(), filterObj.getSortMethod());
		return result;
	}
}
