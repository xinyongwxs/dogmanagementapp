package com.ws.dog.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ws.dog.management.entity.PetDog;

public interface PetDogRepository extends JpaRepository<PetDog, Long>, CustomRepository<PetDog> {

}
