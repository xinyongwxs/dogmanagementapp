package com.ws.dog.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ws.dog.management.entity.BreedingDog;

public interface BreedingDogRepository extends JpaRepository<BreedingDog, Long>, CustomRepository<BreedingDog> {

}
